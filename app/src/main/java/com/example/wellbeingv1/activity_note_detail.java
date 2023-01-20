package com.example.wellbeingv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class activity_note_detail extends AppCompatActivity {
    EditText titleText, contentText;
    ImageButton saveNoteButton;
    TextView pageHeaderView;
    String title, content, docId;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        if (docId!=null && !docId.isEmpty()){
            isEdit = true;
        }
        titleText = findViewById(R.id.title_text);
        contentText = findViewById(R.id.content_text);

        saveNoteButton = findViewById(R.id.save_note_button);

        saveNoteButton.setOnClickListener((v)-> saveNote());

        pageHeaderView.findViewById(R.id.note_title);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");
        titleText.setText(title);
        contentText.setText(content);

        if (isEdit){
            pageHeaderView.setText("Edit Journal Entry");
        }


    }

    void saveNote(){
        String noteTitle = titleText.getText().toString();
        String noteContent = contentText.getText().toString();
        if (noteTitle == null||noteTitle.isEmpty()){
            titleText.setError("Title Required");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteFirebase(note);
    }

    void saveNoteFirebase(Note note){
        DocumentReference documentReference;
        if (isEdit){
            //update
            documentReference = Utilities.getCollectionReferenceForNotes().document(docId);

        }else{
            //new
            documentReference = Utilities.getCollectionReferenceForNotes().document();

        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //success
                    Utilities.toaster(activity_note_detail.this, "Entry Saved");
                    finish();
                }else{
                    //fail
                    Utilities.toaster(activity_note_detail.this, "Error not saved");
                }
            }
        });
    }
}