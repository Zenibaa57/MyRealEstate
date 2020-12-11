package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Property implements Serializable {

    @PrimaryKey(autoGenerate = true) public int id;
    public String type;
    public long price;
    public long surfaceArea;
    public int numberOfRoom;
    public String description;
    public String address;;
    public long latitude;;
    public long longitude;;
    public long dateOfTheCreationAdvert;
    public long dateOfTheUpdateAdvert;
    public boolean status;
    public String nameAgent;

    public Property(String type, long price, long surfaceArea, int numberOfRoom, String description,
                    String address, long latitude, long longitude, long dateOfTheCreationAdvert,
                    long dateOfTheUpdateAdvert, boolean status, String nameAgent)
    {
        id = 0;
        this.type = type;
        this.price = price;
        this.surfaceArea = surfaceArea;
        this.numberOfRoom = numberOfRoom;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateOfTheCreationAdvert = dateOfTheCreationAdvert;
        this.dateOfTheUpdateAdvert = dateOfTheUpdateAdvert;
        this.status = status;
        this.nameAgent = nameAgent;
    }

}
