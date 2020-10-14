package com.example.notes_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {

        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    LiveData<List<Note>> getAllWords() {
        return mAllNotes;
    }

    public void insert(Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }
}
