package com.example.lab.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RentalItemDao {

    @Query("SELECT * FROM rental_items WHERE userId = :userId")
    LiveData<List<RentalItem>> getRentals(int userId);

    @Insert
    void insertAll(RentalItem... rentalItems);

    @Delete
    void delete(RentalItem rentalItem);
}
