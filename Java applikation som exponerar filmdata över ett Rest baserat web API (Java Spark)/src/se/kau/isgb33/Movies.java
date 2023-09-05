package se.kau.isgb33;

import java.io.FileInputStream;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

public class Movies {

	public static void main(String[] args) {
		String connString;
		Logger logger = LoggerFactory.getLogger(Movies.class);
		try (InputStream input = new FileInputStream("connection.properties")) {

			Properties prop = new Properties();
			prop.load(input);
			connString = prop.getProperty("db.connection_string");
			logger.info(connString);

			ConnectionString connectionString = new ConnectionString(connString); //mongodb://localhost:27017
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
					.build();
			MongoClient mongoClient = MongoClients.create(settings);
			MongoDatabase database = mongoClient.getDatabase(prop.getProperty("db.name"));
		    MongoCollection<Document> collection= database.getCollection("movies");

			
			get("/title/:title", (req,res)->{
	    	      res.type("application/json");

			      Bson filter = Filters.in("title", req.params("title")); 
		    	  Bson projectionFields = Projections.fields(
		                    Projections.exclude("_id","poster","cast","fullplot"));
			      
		          Document doc = collection.find(filter).projection(projectionFields).first();
		            
		          if(doc == null) {
		        	  res.status(404);		      
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
			          return doc.toJson();	
		          }
			     
		      });
			
			
			get("/fullplot/:title", (req,res)->{
	    	      res.type("application/json");

		    	  Bson filter = Filters.in("title", req.params("title"));
		    	  Bson projectionFields = Projections.fields(
		                    Projections.include("fullplot"),
		                    Projections.excludeId());
			      
		          Document doc = collection.find(filter).projection(projectionFields).first();
		            
		          if(doc == null) {
		        	  res.status(404);
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
			          return doc.toJson();	
		          }
		          
		      });
			
			
			get("/cast/:title", (req,res)->{
	    	      res.type("application/json");
	
		    	  Bson filter = Filters.in("title", req.params("title"));
		    	  Bson projectionFields = Projections.fields(
		                    Projections.include("cast"),
		                    Projections.excludeId());
			      
		          Document doc = collection.find(filter).projection(projectionFields).first();
		            
		          if(doc == null) {
		        	  res.status(404);
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
			          return doc.toJson();	
		          }
		          
		      });
			
			
			get("/image/:title", (req,res)->{
				  res.type("application/json");
				  
		    	  Bson filter = Filters.in("title", req.params("title"));
		    	  Bson projectionFields = Projections.fields(
		                    Projections.include("title","poster"),
		                    Projections.excludeId());
			      
		          Document doc = collection.find(filter).projection(projectionFields).first();
		            
		          if(doc == null) {
		        	  res.status(404);
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
			          return doc.toJson();	
		          }
		          
		      });
			
			
			get("/genre/:genre", (req,res)->{				  
				  res.type("application/json");
				  
				  int theLimit=10;
				  if(req.queryParams("limit") !=null) {
					theLimit= Integer.parseInt(req.queryParams("limit"));
				  }
		    	 
				  Bson filter = Filters.in("genres", req.params("genre"));
				  
				  AggregateIterable<Document> myDocs = collection.aggregate(Arrays.asList(
						Aggregates.match(filter),	
						Aggregates.project(
								Projections.fields(
									Projections.exclude("_id","poster","cast","fullplot")
										)),
						Aggregates.limit(theLimit)
						));

				  MongoCursor<Document> cursor = myDocs.iterator();	
				
			      if(!cursor.hasNext()) {
					  res.status(404);
		        	  return ("{'Message':'Title not found. Be sure that you type the genre's name right (first letter capital). e.g. Action.'}");
			       }
				
				  JsonArray array=new JsonArray();
		    	  while(cursor.hasNext()) {
					Document doc = cursor.next();
					array.add(doc.toJson());
				  }
		    	
		    	  JsonObject response=new JsonObject();
			      response.add("First "+ theLimit +" Movies for "+ req.params("genre")+ " genre are:",array);
			      return response;

		      });
			
			
			get("/actor/:actor", (req,res)->{
				res.type("application/json");
				
				int theLimit=10;
				if(req.queryParams("limit") !=null) {
					theLimit= Integer.parseInt(req.queryParams("limit"));
				}
				
				Bson filter = Filters.in("cast", req.params("actor"));
				
				AggregateIterable<Document> myDocs = collection.aggregate(Arrays.asList(
						Aggregates.match(filter),
						Aggregates.project(
								Projections.fields(
									Projections.excludeId(),
									Projections.include("title")
										)),
						Aggregates.limit(theLimit)
						));

				MongoCursor<Document> cursor = myDocs.iterator();	
				
			    if(!cursor.hasNext()) {
					 res.status(404);
		        	  return ("{'Message':'Title not found. Be sure that you type the actor's name right (first letter capital). e.g. Jessica Biel.'}");
			     }
				
				JsonArray array=new JsonArray();
		    	while(cursor.hasNext()) {
					Document doc = cursor.next();
					array.add(doc.toJson());
				}
		    	
		    	JsonObject response=new JsonObject();
			    response.add( theLimit +" Movies for "+ req.params("actor"),array);
			    return response;

		      });
			
			
			get("/similar/:title", (req,res)->{
				  res.type("application/json");
				  
				  int theLimit=10;
				  if(req.queryParams("limit") !=null) {
					theLimit= Integer.parseInt(req.queryParams("limit"));
				  }
				  
			      Bson filter = Filters.in("title", req.params("title"));
		    	  
		          Document doc = collection.find(filter).first();
		            
		          if(doc == null) {
		        	  res.status(404);
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
		        	  List <String> genresOfThisMovie = (List <String>) doc.get("genres");
		        	  String genre=genresOfThisMovie.get(0);
		  			  logger.info(genre);
			        
					Bson filter2 = Filters.in("genres", genre);
					
		  			AggregateIterable<Document> myDocs = collection.aggregate(Arrays.asList(
							Aggregates.match(filter2),	
							Aggregates.project(
									Projections.fields(
										Projections.exclude("_id","poster","cast","fullplot")
											)),
							Aggregates.limit(theLimit)
							));

					MongoCursor<Document> cursor = myDocs.iterator();	
					
				    if(!cursor.hasNext()) {
						 res.status(404);
				     }
					
					JsonArray array=new JsonArray();
			    	while(cursor.hasNext()) {
						Document doc2 = cursor.next();
						array.add(doc2.toJson());
					}
			    	
			    	JsonObject response=new JsonObject();
				    response.add("First "+theLimit+ " similar movies for "+ req.params("title"),array);
				    return response;
		  			  	  		  
		          }
			     
		      });
			
			
			post("/title", (req, res) -> {
	    	      res.type("application/json");
	    	    	    	   
	    	      try {
	    	    	  collection.insertOne(new Document(Document.parse(req.body())));
		 		      logger.info("One movie added successfully "); 
	              } catch (MongoException me) {
	                  System.err.println("Unable to insert due to an error: " + me);
	              }
	        	  
	    	      res.status(202);
	        	  return ("");
	    	});
			
			
			delete("/title/:title", (req,res)->{
			      
			      Bson filter = Filters.in("title", req.params("title"));
		    	    
		          Document doc = collection.find(filter).first();
		            
		          if(doc == null) {
		        	  res.status(409);
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
		        	  
		        	  try {
		                  collection.deleteOne(doc);
			 		      logger.info("The Movie "+ req.params("title") + " deleted successfully from the collection"); 

		              } catch (MongoException me) {
		                  System.err.println("Unable to delete due to an error: " + me);
		              }
		        	  
		        	  res.status(204);
		        	  return ("");
		          }
			     
		      });
			
			put("/title/:title", (req,res)->{
			      
			      Bson filter = Filters.in("title", req.params("title"));
		    	    
		          Document doc = collection.find(filter).first();
		            
		          if(doc == null) {
		        	  res.status(409);
		        	  return ("{'Message':'Title not found. Be sure that you type the movie's title right.'}");
		          }else {
		        	  
		        	  try {
		                  collection.replaceOne(doc,Document.parse(req.body()));
			 		      logger.info("The Movie "+ req.params("title") + " replaced successfully with another document that came from the request"); 

		              } catch (MongoException me) {
		                  System.err.println("Unable to replace due to an error: " + me);
		              }
		        	  
		        	  res.status(202);
		        	  return ("");
		          }
			     
		      });
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


