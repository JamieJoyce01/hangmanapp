package com.example.firstandroidapp;

import android.content.Intent;
import android.view.View;

import java.util.Arrays;
import java.util.Random;

public class Hangman {

    GameScreen gs;

    String chars = "";
    String word;
    String[][] words = {
            {"jeff","month","world","heart","pizza","water","board","angel","magic","death","music"},
            {"purple","orange","family","silver","people","future","heaven","banana","africa","office"},
            {"freedom","perfect","country","pumpkin","special","america","picture","husband","monster","nothing"}
    };
    char[] secretarray, wordarray;
    int picstage;
    Boolean correct;
    private int[] imagearray = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.fifth,
            R.drawable.sixth,
            R.drawable.seventh,
            R.drawable.eigth,
            R.drawable.nineth,
            R.drawable.tenth,
            R.drawable.eleventh
    };

    public Hangman(GameScreen gs) {
        this.gs = gs;
    };

    public void prep() {
        chars = "";
        picstage = 0;

        Random random = new Random();
        word = words[this.gs.stage-1][random.nextInt(words.length)];
        wordarray= word.toCharArray();
        secretarray = new char[word.length()];
        Arrays.fill(secretarray, '_');

        this.gs.chars.setText(chars);
        this.gs.image.setBackgroundResource(imagearray[picstage]);
        this.wordprocess();

    }

    public void guess() {
        Character letter = Character.toLowerCase(this.gs.wordsubmit.getText().charAt(0));
        correct = false;

        for(int i = 0; i < wordarray.length; i++) {
            if (letter == wordarray[i]) {
                secretarray[i]=letter;
                correct = true;
            };
        };
        this.wordprocess();
        if (picstage >= 9) {
            // Lose if
            picstage++;
            this.gs.popup_lost();
        }
        else if (Arrays.equals(secretarray, wordarray)) {
            // Win if
            this.gs.popup_win();
        }
        else if (correct == false) {
            picstage++;
            chars += letter;
        }

        this.gs.chars.setText(chars);
        this.gs.image.setBackgroundResource(imagearray[picstage]);
    };

    private void wordprocess() {
        String secretword = "";
        for (char c : secretarray) {
            secretword += c;
        }
        this.gs.text.setText(secretword);
    };
};
