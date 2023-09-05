package com.example.myapplication_hangman;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    Data data=new Data();
    TextView nbrOfLettersMessage,userInputView,msgToThePlayer;
    EditText theInput;
    Button enter,playAgain;
    private String theWord;
    private int countWrong;
    ImageView imageView;
    private int[] imagesArray;


    @SuppressLint("SetTextI18n") //denna rad läggs av android studio för att ta bort en varning
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nbrOfLettersMessage=findViewById(R.id.nbrOfLettersMessage);
        userInputView=findViewById(R.id.userInputView);
        imagesArray=new int[]{ R.drawable.game0, R.drawable.game1,
                               R.drawable.game2, R.drawable.game3,
                               R.drawable.game4, R.drawable.game5,
                               R.drawable.game6};
        theWord = data.getWord(); //hämtar en slumpmässig ord
        countWrong=7;
        startTheGame();
    }

    @SuppressLint("SetTextI18n") //denna rad läggs av android studio för att ta bort en varning
    public void startTheGame(){
        Log.d("The Word", "is: " + theWord  + " With" +theWord.length()+ " Letters");

        nbrOfLettersMessage.setText("The animal name consist of " +(theWord.length())+ " letters");
        String printDash="";
        for(int i=0;i<theWord.length();i++) {
            printDash= printDash + "-";
        }
        userInputView.setText(printDash);
    }

    public void clicked(View view) {
        theInput = findViewById(R.id.charInput);
        String value = theInput.getText().toString();

        if(value.isEmpty()){
            Toast.makeText( getBaseContext(), "Enter one char!",Toast.LENGTH_SHORT).show();
        }
        else {
            char theChar = value.charAt(0);
            Log.d("The char", "is: " + theChar);
            if(data.userInputs.contains(theChar)){ //om användaren har använt denna bokstav(finns i min char lista så jag ger honom/henne en tillfälle att använda annan bokstav utan att öka fel antal)
                Toast.makeText( getBaseContext(), "You have tried this char, try another one",Toast.LENGTH_SHORT).show();
                theInput.setText("");
            }
            else {
                matchTheInput(theChar);
                checkIfGameOver();
            }
        }

    }

    @SuppressLint("SetTextI18n")//denna rad läggs av android studio för att ta bort en varning
    private void matchTheInput(char theChar) {
        data.addChar(theChar); // lägga till denna bokstav till min char lista
        if(theWord.contains(String.valueOf(theChar))) { //Om ordet innehåller denna bokstav
            String userView=userInputView.getText().toString();

            for(int i = 0;i < userView.length(); i++) { //loopa igenom varje bokstav(dash)
                if(theChar == theWord.charAt(i)) {      //om denna bokstav matchar en bokstav som finns i ordet
                    userView = userView.substring(0, i) + theChar + userView.substring(i+1);// denna rad hämtades från
                    Toast.makeText( getBaseContext(), "Good one",Toast.LENGTH_SHORT).show();
                }
            }
            Log.d("rr", "is: " + userView);
            userInputView.setText(userView); //uppdate the InputView
        }

        else {              //annars öka fel antal med msg till användaren
            imageView=findViewById(R.id.imgGame);
            Toast.makeText( getBaseContext(), "Wrong",Toast.LENGTH_SHORT).show();
            countWrong--;
            Log.d("WrongCount", "is: " + countWrong );
            imageView.setImageResource(imagesArray[countWrong]);

        }

        theInput.setText("");
        msgToThePlayer=findViewById(R.id.informationMsg);
        msgToThePlayer.setText("You have just "+ countWrong +" Wrong left"); //information till användaren
    }

    @SuppressLint("SetTextI18n")//denna rad läggs av android studio för att ta bort en varning
    private void checkIfGameOver() {
        String set=userInputView.getText().toString();
        msgToThePlayer=findViewById(R.id.informationMsg);

        if(countWrong==0 ){ //om hen förlorar
            msgToThePlayer.setText("You lost, The Word is "+theWord);
            playAgain=findViewById(R.id.playAgain);
            playAgain.setVisibility(View.VISIBLE);
            enter=findViewById(R.id.click);
            enter.setEnabled(false);
        }
        else if(!set.contains("-")){ //om hen vinner
            msgToThePlayer.setText("You Won, Good job!");
            playAgain=findViewById(R.id.playAgain);
            playAgain.setVisibility(View.VISIBLE);
            enter=findViewById(R.id.click);
            enter.setEnabled(false);
        }
    }
    public void plyClicked(View view) { //starta om spelet
        finish();
    }
}