package com.example.firstandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameScreen extends AppCompatActivity {

    ImageView image;
    TextView text,chars,round;
    Button submitbutton;
    EditText wordsubmit;

    int stage = 1;

    private AlertDialog.Builder dialogBuilder;
    private Dialog dialog;
    private TextView poptitle;
    private ImageView popimage;
    private TextView popdesc;
    private Button popbutton;

    Hangman hangman = new Hangman(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        image = (ImageView)findViewById(R.id.hangmanimage);
        text = (TextView)findViewById(R.id.worddisplay);
        submitbutton = (Button)findViewById(R.id.submitbutton);
        chars = (TextView)findViewById(R.id.chars);
        round = (TextView)findViewById(R.id.round);


        setstage();

        hangman.prep();
    }

    public void setstage() {
        String output = String.format("Stage %d/3", stage);
        round.setText(output);
    }
    public void buttonclick(View view) {
        wordsubmit = (EditText)findViewById(R.id.wordsubmit);
        if (!wordsubmit.getText().equals("") && wordsubmit.getText().length() > 0) {
            hangman.guess();
        }
        wordsubmit.setText("");
    };

    public void viewTitleScreen() {
        Intent titleScreen = new Intent(this, TitleScreen.class);
        startActivity(titleScreen);
    }

    public void popup_lost() {
        dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.popuptheme));
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_lost, null);

        poptitle = (TextView) contactPopupView.findViewById(R.id.popup_lost_title);
        popbutton = (Button) contactPopupView.findViewById(R.id.popup_lost_button);
        popimage = (ImageView) contactPopupView.findViewById(R.id.popup_lost_image);
        popdesc = (TextView) contactPopupView.findViewById(R.id.popup_lost_desc);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);

        popbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                stage = 1;
                hangman.prep();
            }
        });
    }

    public void popup_win() {
        dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.popuptheme));
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_win, null);

        poptitle = (TextView) contactPopupView.findViewById(R.id.popup_win_title);
        popbutton = (Button) contactPopupView.findViewById(R.id.popup_win_button);
        popimage = (ImageView) contactPopupView.findViewById(R.id.popup_win_image);
        popdesc = (TextView) contactPopupView.findViewById(R.id.popup_win_desc);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);

        popbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                stage++;
                if (stage > 3) {
                    //win code here
                }
                else {
                    setstage();
                    hangman.prep();
                };
            }
        });
    }

}