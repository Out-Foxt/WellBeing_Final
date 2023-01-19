package com.example.wellbeingv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_journal extends AppCompatActivity {

    FloatingActionButton addNoteButton;
    RecyclerView recyclerView;
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        addNoteButton = findViewById(R.id.add_note_button);
        addNoteButton.setOnClickListener((v) -> startActivity(new Intent(activity_journal.this,activity_note_detail.class)));

        recyclerView = findViewById(R.id.recyclerView);
        menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener((v) -> showMenu() );

        setRecyclerView();
    }

    void showMenu(){

    }
    void setRecyclerView(){

    }
}