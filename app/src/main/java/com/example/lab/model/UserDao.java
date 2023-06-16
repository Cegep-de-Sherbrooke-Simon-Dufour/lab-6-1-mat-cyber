package com.example.lab.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    LiveData<List<User>> getUsers();

    @Insert
    void insertAll(User... user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM users WHERE uid = :userId")
    void deleteByUserId(int userId);
}
