package com.example.harrylum.cardistry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.card_backgroundw));
                findViewById(R.id.flashcard_answer2).setBackground(getResources().getDrawable(R.drawable.card_backgroundy));
            }
        });
        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.card_backgroundy));
            }
        });
        findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.card_backgroundw));
                findViewById(R.id.flashcard_answer2).setBackground(getResources().getDrawable(R.drawable.card_backgroundy));
            }
        });
        findViewById(R.id.visibilityoff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);
                v.setVisibility(View.INVISIBLE);
                findViewById(R.id.visibilityon).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.visibilityon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);
                v.setVisibility(View.INVISIBLE);
                findViewById(R.id.visibilityoff).setVisibility(View.VISIBLE);
            }
        });

    }

}
