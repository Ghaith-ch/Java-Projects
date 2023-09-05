package com.example.Labb4_JSON_Movies;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements VolleyCallback{

    private EditText theInput;
    private Button search,goToFav;
    private ListView moviesList;

    private CustomListAdapter adapter;

    private RequestQueue requestQueue;
    private  APICall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theInput=findViewById(R.id.input);
        search=findViewById(R.id.search);
        moviesList=findViewById(R.id.listOfMovies);
        goToFav=findViewById(R.id.fav);

        goToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, favorite_movie.class);
                startActivity(i);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(theInput.getText().toString().isEmpty()) {
                    Toast.makeText( getBaseContext(), "Type a movie title!",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
                    //Network network = new BasicNetwork(new HurlStack());
                    //requestQueue = new RequestQueue(cache, network);
                    //requestQueue.start();

                    String artistName = theInput.getText().toString();
                    artistName = artistName.replace(" ", "+");
                    String url = "https://www.myapifilms.com/imdb/idIMDB?title="+artistName+
                            "&token=b3aeb5f2-9dcd-49a9-84d3-9a788e2440fe&limit=10&similarMovies=1";

                    requestQueue = Volley.newRequestQueue(MainActivity.this);
                    apiCall = new APICall();
                    apiCall.get(requestQueue, MainActivity.this, url);
                }
            }
        });
    }

    @Override
    public void onSucess(JSONObject response) {
         ArrayList<Movie> listOfMovie =new ArrayList<>();

        try {
            JSONObject data = response.getJSONObject("data");
            JSONArray movies = data.getJSONArray("movies");

            String title,id, urlToMovie;

            for(int i=0;i<movies.length();i++) {
                JSONObject aMovie = movies.getJSONObject(i);
                title = aMovie.get("title").toString();
                id =  aMovie.get("idIMDB").toString() ;
                urlToMovie = aMovie.get("urlIMDB").toString();

                Movie oneMovie = new Movie(id,title,urlToMovie);
                listOfMovie.add(oneMovie);
            }
            adapter=new CustomListAdapter(this, R.layout.list_item, listOfMovie);
            moviesList.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText( getBaseContext(),"ops!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Exception e) {
        Log.e("ERROR FAIL", e.toString());
    }
}
