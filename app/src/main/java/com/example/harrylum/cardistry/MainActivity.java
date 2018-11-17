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

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            int rand = getRandomNumber(0,allFlashcards.size()-1);
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(rand).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(rand).getAnswer());
            ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(rand).getWrongAnswer1());
            ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(rand).getWrongAnswer2());
        }

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.card_backgroundy));

            }
        });
        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.card_backgroundw));
                findViewById(R.id.flashcard_answer1).setBackground(getResources().getDrawable(R.drawable.card_backgroundy));
            }
        });
        findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(getResources().getDrawable(R.drawable.card_backgroundw));
                findViewById(R.id.flashcard_answer1).setBackground(getResources().getDrawable(R.drawable.card_backgroundy));
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
                intent.putExtra("edit","yeet");
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (allFlashcards.size() == 0){
                    ((TextView) findViewById(R.id.flashcard_question)).setText("Add a card to get started!");
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText("");
                }else {
                    // set the question and answer TextViews with data from the database
                    int rand = getRandomNumber(0, allFlashcards.size() - 1);
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(rand).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(rand).getAnswer());
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(rand).getWrongAnswer1());
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(rand).getWrongAnswer2());
                }
            }
        });

        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                if (allFlashcards.size() == 0) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText("Add a card to get started!");
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText("");
                }else{
                    findViewById(R.id.next_button).performClick();
                }

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

                if (data.getExtras().getString("edit") != null) {
                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                }

                ((TextView) findViewById(R.id.flashcard_question)).setText(question);
                ((TextView) findViewById(R.id.flashcard_answer1)).setText(answer1);
                ((TextView) findViewById(R.id.flashcard_answer2)).setText(answer2);
                ((TextView) findViewById(R.id.flashcard_answer3)).setText(answer3);

                flashcardDatabase.insertCard(new Flashcard(question, answer1,answer2,answer3));
                allFlashcards = flashcardDatabase.getAllCards();
                Snackbar.make(findViewById(R.id.RelativeLayout), "Created card successfully", Snackbar.LENGTH_LONG)
                        .show(); // Don’t forget to show!
            }catch(Exception e){

            }
        }
    }

}
