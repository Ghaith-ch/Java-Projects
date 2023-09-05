package com.example.labb3_xml;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;

public class Control {

    private Database database;

    public Control(MainActivity v){
        database=new Database();
    }

    public void getDataAndSaveItInDatabase(String artistName,String numberOfSimilar){
        URL url;
        String API_KEY = "63fc66cd0f2f181e2ef81c6ce766f9de";
        try{
            url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&limit="
                    +numberOfSimilar + "&artist=" + artistName + "&api_key=" + API_KEY);

            Log.d("artistName ",  artistName);
            Log.d("apiKey ",  API_KEY);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            String tagName;

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                if(parserEvent == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if(tagName.equals("name")) {
                        parser.next();
                        String nameOfTheArtist = parser.getText();
                        Log.d("Found an artist and the name of this artist is: ", nameOfTheArtist);
                        database.addToList(nameOfTheArtist);
                    }
                }
                parserEvent = parser.next();
            }
        }
        catch(IOException ex){
            Log.e("Error", "IOException: " + ex);
        }
        catch(Exception ex){
            Log.e("Error", "Unknown2: " + ex);
        }
    }

    public String getTheListFromDatabase() {
      return database.printArtists();
    }

    public void ClearTheList() {
        database.removeAllItem();
    }
}
