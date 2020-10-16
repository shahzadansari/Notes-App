package com.example.notes_app.async;

import android.os.AsyncTask;

import com.example.notes_app.models.Note;
import com.example.notes_app.persistence.NoteDao;

public class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

    private NoteDao mAsyncTaskDao;

    public InsertAsyncTask(NoteDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mAsyncTaskDao.insert(notes[0]);
        return null;
    }
}
