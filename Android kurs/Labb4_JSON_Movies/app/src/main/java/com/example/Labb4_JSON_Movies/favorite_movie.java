package com.example.Labb4_JSON_Movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class favorite_movie extends AppCompatActivity {

    private TextView message;
    private Button back;
    private ListView favMoviesList;

    private FavoriteListAdapter adapter;
    private ArrayList<String> listOfFavMovies;


    private FileHandling file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);

        message=findViewById(R.id.message);
        back=findViewById(R.id.back);
        favMoviesList=findViewById(R.id.listFavorite);
        file=new FileHandling(this);
        listOfFavMovies=new ArrayList<>();

        listOfFavMovies=file.readTheFile();

        adapter=new FavoriteListAdapter(this, R.layout.fav_list_item, listOfFavMovies);
        favMoviesList.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(favorite_movie.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}