package com.example.lab.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "rental_items",
        foreignKeys = {
            @ForeignKey(
                    entity = User.class,
                    parentColumns = "uid",
                    childColumns = "userId",
                    onDelete = ForeignKey.CASCADE
            )
        })
public class RentalItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int userId;

    public RentalItem(String name, int userId){
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}