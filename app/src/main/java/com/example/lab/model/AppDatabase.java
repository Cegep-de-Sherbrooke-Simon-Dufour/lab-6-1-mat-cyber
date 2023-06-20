package com.example.lab.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, RentalItem.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract RentalItemDao getRentalItemDao();
}
