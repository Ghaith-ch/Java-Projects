package com.example.Labb4_JSON_Movies;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
public class FavoriteListAdapter extends ArrayAdapter<String> {

    private int layout;
    private List<String> favmovies;

    public FavoriteListAdapter(@NonNull Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        layout = resource;
        favmovies = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(layout, parent, false);

        TextView text = convertView.findViewById(R.id.list_item_text_fav);
        text.setText(favmovies.get(position));

        return convertView;
    }
}
