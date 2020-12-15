package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

    @Entity
    public class PropertyStatus implements Serializable {

        @PrimaryKey(autoGenerate = true)
        public int id;
        public boolean isAvaible;

        public PropertyStatus(int id, boolean isAvaible) {
            this.id = 0;
            this.isAvaible = isAvaible;
        }
    }

