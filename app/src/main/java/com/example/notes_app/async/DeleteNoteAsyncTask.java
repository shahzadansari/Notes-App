package com.example.notes_app.async;

import android.os.AsyncTask;

import com.example.notes_app.models.Note;
import com.example.notes_app.persistence.NoteDao;

public class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDao myAsyncTaskDao;

    public DeleteNoteAsyncTask(NoteDao noteDao) {
        myAsyncTaskDao = noteDao;
    }

    @Override
    protected Void doInBackground(final Note... notes) {
        myAsyncTaskDao.deleteNote(notes[0]);
        return null;
    }
}