package com.example.wellbeingv1;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapterUtility extends FirestoreRecyclerAdapter<Note, NoteAdapterUtility.viewHolder> {
    Context context;

    public NoteAdapterUtility(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Note note) {
        holder.titleTextView.setText(note.title);
        holder.contentTextView.setText(note.content);
        holder.timestampTextView.setText(Utilities.timeToString(note.timestamp));

        holder.itemView.setOnClickListener((v) -> {
            Intent intent = new Intent(context, activity_note_detail.class);
            intent.putExtra("title", note.title);
            intent.putExtra("content", note.content);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note,
                parent, false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView, contentTextView, timestampTextView;

        public viewHolder(@NonNull View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            contentTextView = itemView.findViewById(R.id.content_text_view);
            timestampTextView = itemView.findViewById(R.id.timestamp_text_view);
        }
    }
}
