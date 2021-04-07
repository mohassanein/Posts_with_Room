package com.example.postswithroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface PostsDao {
    @Insert
    Completable insertPost(PostModel postModel);

    @Query("select * from posts_table")
    Observable<List<PostModel>> getPost();


}
