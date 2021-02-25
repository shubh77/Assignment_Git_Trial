package com.example.assignment.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.model.UserModel;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@Dao
public interface UserDao {

    @Insert
    void insert(UserModel user);
//    Void insert(UserModel user);

    @Update
    void update(UserModel user);

    @Delete
    void delete(UserModel user);

    @Query("DELETE FROM UserModel")
    void deleteAll();

    @Query("SELECT * FROM UserModel ORDER BY id DESC")
    Flowable<List<UserModel>> getAll();






}
