package com.example.labb3_xml;
import android.util.Log;

import java.util.ArrayList;

public class Database {

    private ArrayList<String> artists;
    private String print;

    public Database (){
        artists=new ArrayList<>();
    }

    public void addToList(String nameToAdd) {
        artists.add(nameToAdd);
    }

    public void removeAllItem(){
        artists.clear();
    }

    public String printArtists(){
        print= "";
        for(int i = 0; i < artists.size(); i++){
            print += artists.get(i) + "\n";
        }
        return print;
    }
}
