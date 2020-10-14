package com.example.notes_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes-table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String body;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
