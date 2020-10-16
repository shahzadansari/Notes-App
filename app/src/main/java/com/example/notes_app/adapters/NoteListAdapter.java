package com.example.notes_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.R;
import com.example.notes_app.models.Note;

public class NoteListAdapter extends ListAdapter<Note, NoteListAdapter.NoteViewHolder> {

    private OnItemClickListener listener;

    public NoteListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getBody().equals(newItem.getBody()));
        }
    };

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (getItem(position) != null) {
            Note currentNote = getItem(position);
            holder.titleTextView.setText(currentNote.getTitle());
            holder.bodyTextView.setText(currentNote.getBody());
        } else {
            // Covers the case of data not being ready yet.
            holder.titleTextView.setText("No Title");
            holder.bodyTextView.setText("No Body");
        }
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView bodyTextView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_note_title);
            bodyTextView = itemView.findViewById(R.id.text_view_note_body);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
