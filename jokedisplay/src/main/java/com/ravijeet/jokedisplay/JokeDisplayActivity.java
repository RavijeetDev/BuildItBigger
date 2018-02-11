package com.ravijeet.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private final static String JOKE = "JOKE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        TextView jokeTextView = findViewById(R.id.jokeTextView);
        Intent intent = getIntent();

        if(intent != null){
            String joke = intent.getStringExtra(JOKE);
            jokeTextView.setText(joke);
        }
    }
}