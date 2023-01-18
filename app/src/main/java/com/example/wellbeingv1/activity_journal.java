package com.example.wellbeingv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_journal extends AppCompatActivity {

    FloatingActionButton addNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        addNoteButton = findViewById(R.id.add_note_button);
        addNoteButton.setOnClickListener((v) -> startActivity(new Intent(activity_journal.this,activity_note_detail.class)));


    }
}