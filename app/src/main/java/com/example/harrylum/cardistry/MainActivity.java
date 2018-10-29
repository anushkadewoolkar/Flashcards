package com.example.harrylum.cardistry;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addflashcard.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addflashcard.class);
                intent.putExtra("question",((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("answer1",((TextView) findViewById(R.id.flashcard_answer1)).getText().toString());
                intent.putExtra("answer2",((TextView) findViewById(R.id.flashcard_answer2)).getText().toString());
                intent.putExtra("answer3",((TextView) findViewById(R.id.flashcard_answer3)).getText().toString());
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            try {
                String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
                String answer1 = data.getExtras().getString("answer1");
                String answer2 = data.getExtras().getString("answer2");
                String answer3 = data.getExtras().getString("answer3");
                ((TextView) findViewById(R.id.flashcard_question)).setText(question);
                ((TextView) findViewById(R.id.flashcard_answer1)).setText(answer2);
                ((TextView) findViewById(R.id.flashcard_answer2)).setText(answer1);
                ((TextView) findViewById(R.id.flashcard_answer3)).setText(answer3);
                Snackbar.make(findViewById(R.id.RelativeLayout), "Created card successfully", Snackbar.LENGTH_LONG)
                        .show(); // Don’t forget to show!
            }catch(Exception e){

            }
        }
    }

}
