package com.example.notes_app.async;

import android.os.AsyncTask;

import com.example.notes_app.models.Note;
import com.example.notes_app.persistence.NoteDao;
import com.example.notes_app.persistence.NoteRoomDatabase;

public class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

    private final NoteDao mDao;

    // Initial data set
    private static Note[] notes = {new Note("Note 1 Title", "Note 1 Body"),
            new Note("Note 2 Title", "Note 2 Body"),
            new Note("Note 3 Title", "Note 3 Body"),
            new Note("Note 4 Title", "Note 4 Body"),
            new Note("Note 5 Title", "Note 5 Body")};

    public PopulateDbAsyncTask(NoteRoomDatabase db) {
        mDao = db.noteDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {

        for (int i = 0; i <= notes.length - 1; i++) {
            Note note = new Note(notes[i].getTitle(), notes[i].getBody());
            mDao.insert(note);
        }
        return null;
    }
}