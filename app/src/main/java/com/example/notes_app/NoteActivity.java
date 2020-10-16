package com.example.notes_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes_app.models.Note;
import com.example.notes_app.persistence.NoteRepository;

public class NoteActivity extends AppCompatActivity {
    private Button button;
    private EditText editTextField1;
    private EditText editTextField2;

    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mNoteRepository = new NoteRepository(getApplication());

        editTextField1 = findViewById(R.id.edit_text_note_title);
        editTextField2 = findViewById(R.id.edit_text_note_body);
        button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextField1.getText().toString().trim();
                String body = editTextField2.getText().toString().trim();
                Note note = new Note(title, body);

                mNoteRepository.insert(note);
                Toast.makeText(NoteActivity.this, "Note inserted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}