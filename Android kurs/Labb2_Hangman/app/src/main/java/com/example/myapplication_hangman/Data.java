package com.example.myapplication_hangman;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Data {

    List<String> listOfWords=new ArrayList<>
            (Arrays.asList("cow","rabbit","ducks","horse","pig","sheep","turkey","chicken","camels","cat","dog",
                           "panda","lion","mouse","monkey","koala","walrus","ox","raccoon","fox","giraffe","deer" ));
    List<Character> userInputs = new ArrayList<>();

    public String getWord() {
        Random r = new Random();
        String word=listOfWords.get(r.nextInt(listOfWords.size()));
        return word;
    }

    public void addChar(char c){
        userInputs.add(c);
    }
}
