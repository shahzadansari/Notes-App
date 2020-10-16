package com.example.notes_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.notes_app.models.Note;
import com.example.notes_app.persistence.NoteViewModel;

public class NoteActivity extends AppCompatActivity {
    private Button button;
    private EditText editTextNoteTitle;
    private EditText editTextNoteBody;

    private NoteViewModel mNoteViewModel;

    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        editTextNoteTitle = findViewById(R.id.edit_text_note_title);
        editTextNoteBody = findViewById(R.id.edit_text_note_body);
        button = findViewById(R.id.button_save);

        if (getIntent().getExtras() != null) {
            Note note = getIntent().getParcelableExtra("selected_note");
            id = note.getId();
            editTextNoteTitle.setText(note.getTitle());
            editTextNoteBody.setText(note.getBody());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextNoteTitle.getText().toString().trim();
                String body = editTextNoteBody.getText().toString().trim();

                Note note = new Note(title, body);
                if (id != -1) {
                    note.setId(id);
                    mNoteViewModel.updateNote(note);
                    Toast.makeText(NoteActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
                } else {
                    mNoteViewModel.insert(note);
                    Toast.makeText(NoteActivity.this, "New Note inserted", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }
}