package com.example.Labb4_JSON_Movies;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class FileHandling {

    private static final String FileName="favorites.txt";
    private Context context;

    public FileHandling(Context context){
        this.context=context;
    }

    public void WriteToFile(String titleOfTheMovie){
        titleOfTheMovie= titleOfTheMovie+ "\n";

        try {
            FileOutputStream fos = context.openFileOutput(FileName,Context.MODE_APPEND);
            fos.write(titleOfTheMovie.getBytes(StandardCharsets.UTF_8));
            fos.close();
            Toast.makeText( context,"File path is: " + context.getFilesDir()+"/"+FileName,Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readTheFile() {
        ArrayList<String> favoriteMoveis=new ArrayList<>();
        String line; //title of one favorite movie

        try{
            InputStream in=context.openFileInput(FileName);
            if(in != null){
                InputStreamReader input=new InputStreamReader(in);
                BufferedReader buff =new BufferedReader(input);
                while((line=buff.readLine()) != null){
                    favoriteMoveis.add(line);
                }
            }
            if (in != null) {
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return favoriteMoveis;
    }
}
