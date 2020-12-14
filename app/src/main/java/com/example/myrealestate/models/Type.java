package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Type implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String type;

    public Type(int id, String type) {
        this.id = 0;
        this.type = type;
    }
}
