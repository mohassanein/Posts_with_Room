package com.example.postswithroom;

import android.content.Context;
import android.content.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities=PostModel.class,version = 1,exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {
    private static PostDatabase postDatabase;
    private final static String DB_NAME = "Posts_Database";
    public abstract PostsDao postsDao();

    public static synchronized PostDatabase getInstance(Context context){
        if(postDatabase==null){
            postDatabase = Room.databaseBuilder(context,PostDatabase.class,DB_NAME)
                   // .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return postDatabase;
    }
}
