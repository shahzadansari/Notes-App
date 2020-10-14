package com.example.notes_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    public static NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "notes-database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;

        // Initial data set
        private static Note[] notes = {new Note("Note 1 Title", "Note 1 Body"),
                new Note("Note 2 Title", "Note 2 Body"),
                new Note("Note 3 Title", "Note 3 Body"),
                new Note("Note 4 Title", "Note 4 Body"),
                new Note("Note 5 Title", "Note 5 Body")};

        PopulateDbAsync(NoteRoomDatabase db) {
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
}
