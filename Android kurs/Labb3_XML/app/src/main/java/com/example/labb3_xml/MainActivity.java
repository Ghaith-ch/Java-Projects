package com.example.labb3_xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText theInput,theNumber;
    private Button search;
    private TextView theList;
    private Control control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        control=new Control(this);

        theInput=findViewById(R.id.input);
        theNumber=findViewById(R.id.inputNumber);
        search=findViewById(R.id.search);
        theList=findViewById(R.id.myList);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("view Now",  theList.getText().toString());

                if(theInput.getText().toString().isEmpty() || theNumber.getText().toString().isEmpty()) {
                    Toast.makeText( getBaseContext(), "All field are required!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    String artistName = theInput.getText().toString();
                    String numberOfSimilar = theNumber.getText().toString();
                    artistName = artistName.replace(" ", "%20");

                    control.getDataAndSaveItInDatabase(artistName,numberOfSimilar);
                    artistList();
                }
                control.ClearTheList();
            }
        });
    }

    private void artistList(){
        String artists = control.getTheListFromDatabase();
        theList.setText(artists);
    }
}