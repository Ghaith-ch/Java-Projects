import java.util.*;

public class Model { 
 
	 public String slumpMedCollection(){
		 Vector<Integer> vektor = new Vector<Integer>();
		 for ( int i = 0; i<35; i++){
			 vektor.add(i+1);
		 }
	
		 Collections.shuffle(vektor);
	
		 String rad="";
		 for(int i = 0; i<7; i++){
			 rad=rad+vektor.get(i)+" ";
		 }
		 return rad;
	 }
 
	 public static void main(String[] args){ 
		  Model l=new Model(); 
		  
		  //Slumpa i Consolen
		  System.out.println(l.slumpMedCollection());
	 } 
}