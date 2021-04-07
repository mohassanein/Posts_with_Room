package com.example.postswithroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts_table")
public class PostModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private String title;
    private String body;


    public PostModel(String date, String title, String body) {
        this.date = date;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
