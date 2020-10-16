package com.example.notes_app.async;

import android.os.AsyncTask;

import com.example.notes_app.persistence.NoteDao;

public class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {

    private NoteDao mAsyncTaskDao;

    public DeleteAllNotesAsyncTask(NoteDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mAsyncTaskDao.deleteAll();
        return null;
    }
}
