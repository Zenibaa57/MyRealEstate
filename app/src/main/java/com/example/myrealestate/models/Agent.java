package com.example.myrealestate.models;

import android.content.Context;
import android.content.res.Resources;

import androidx.core.content.ContextCompat;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.InputStream;
import java.io.Serializable;


@Entity
public class Agent implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String firstname;
    public String lastname;
    public String sexe;
    public String job;
    public int avatar;

    public Agent(String firstname, String lastname, String sexe, String job) {

        id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sexe = sexe;
        this.job = job;
    }

    public int getAvatar() {

        Resources r = null;
        avatar = r.getIdentifier(sexe + "_avatar", "drawable", String.valueOf(this.getClass().getPackage()));
        return avatar;
    }
}
