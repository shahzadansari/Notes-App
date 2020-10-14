package com.example.notes_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
