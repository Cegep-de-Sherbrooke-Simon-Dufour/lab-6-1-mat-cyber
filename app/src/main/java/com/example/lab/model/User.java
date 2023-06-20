package com.example.lab.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "full_name")
    private String name;
    private String email;
    private String uri;

    public User(String name, String email, String uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
