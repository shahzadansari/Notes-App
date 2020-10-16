package com.example.notes_app.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes_app.async.InsertAsyncTask;
import com.example.notes_app.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {

        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        new InsertAsyncTask(mNoteDao).execute(note);
    }
}
