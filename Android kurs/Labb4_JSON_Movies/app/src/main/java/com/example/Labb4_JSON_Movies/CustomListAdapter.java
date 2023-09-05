package com.example.Labb4_JSON_Movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Movie> {

    private int layout;
    private List<Movie> movies;
    private FileHandling filehandling;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Movie> objects) {
        super(context, resource, objects);
        layout = resource;
        movies = objects;
        filehandling=new FileHandling(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(layout, parent, false);

        TextView text = convertView.findViewById(R.id.list_item_text);
        Button button = convertView.findViewById(R.id.list_item_button);

        text.setText(movies.get(position).toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "The Movie: " + movies.get(position).getTitle() +" added to the favorite", Toast.LENGTH_LONG).show();
                String titleOfTheMovie=movies.get(position).getTitle();
                filehandling.WriteToFile(titleOfTheMovie);
            }
        });
        return convertView;
    }
}
