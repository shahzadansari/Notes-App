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
    private EditText editTextField1;
    private EditText editTextField2;

    private NoteViewModel mNoteViewModel;

    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        editTextField1 = findViewById(R.id.edit_text_note_title);
        editTextField2 = findViewById(R.id.edit_text_note_body);
        button = findViewById(R.id.button_save);


        if (getIntent().getExtras() != null) {
            Note note = getIntent().getParcelableExtra("selected_note");
            id = note.getId();
            editTextField1.setText(note.getTitle());
            editTextField2.setText(note.getBody());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextField1.getText().toString().trim();
                String body = editTextField2.getText().toString().trim();

                if (id != -1) {
                    Note note = new Note(title, body);
                    note.setId(id);
                    mNoteViewModel.updateNote(note);
                    Toast.makeText(NoteActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
                } else {
                    Note note = new Note(title, body);
                    mNoteViewModel.insert(note);
                    Toast.makeText(NoteActivity.this, "New Note inserted", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }
}