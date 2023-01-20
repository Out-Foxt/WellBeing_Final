package com.example.wellbeingv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class activity_journal extends AppCompatActivity {

    FloatingActionButton addNoteButton;
    RecyclerView recyclerView;
    ImageButton menuButton;
    NoteAdapterUtility noteAdapter;

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
        Query query = Utilities.getCollectionReferenceForNotes().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapterUtility(options, this);
        recyclerView.setAdapter(noteAdapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        noteAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        noteAdapter.stopListening();
    }
    @Override
    protected void onResume(){
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }
}