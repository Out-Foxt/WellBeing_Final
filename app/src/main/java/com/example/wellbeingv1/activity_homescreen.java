package com.example.wellbeingv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class activity_homescreen extends AppCompatActivity {
    
    Button journalButton, articleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        
        journalButton = findViewById(R.id.journal_button);
        articleButton = findViewById(R.id.articles_button);

        journalButton.setOnClickListener((v)-> startActivity(new Intent(activity_homescreen.this, activity_journal.class)));

        articleButton.setOnClickListener((v)-> startActivity(new Intent(activity_homescreen.this, activity_articles.class)));

    }
}