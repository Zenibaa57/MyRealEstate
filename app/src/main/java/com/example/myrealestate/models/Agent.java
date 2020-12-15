package com.example.myrealestate.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Agent implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String firstname;
    public String lastname;
    public String sexe;
    public String job;


    public Agent(String firstname, String lastname, String sexe, String job) {

        id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sexe = sexe;
        this.job = job;
    }

    public int getAvatar(Context context) {
        return context.getResources().getIdentifier("ic_"+sexe + "_avatar", "drawable", context.getPackageName());
    }
}
