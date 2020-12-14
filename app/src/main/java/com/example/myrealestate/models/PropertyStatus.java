package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

    @Entity
    public class PropertyStatus implements Serializable {

        @PrimaryKey(autoGenerate = true)
        public int id;
        public boolean status;

        public PropertyStatus(int id, boolean status) {
            this.id = 0;
            this.status = status;
        }
    }

