package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Property implements Serializable {

    @PrimaryKey(autoGenerate = true) public int id;
    public long price;
    public long surfaceArea;
    public int numberOfRoom;
    public String description;
    public String address;;
    public long latitude;;
    public long longitude;;
    public long dateOfTheCreationAdvert;
    public long dateOfTheUpdateAdvert;


    public Property(long price, long surfaceArea, int numberOfRoom, String description,
                    String address, long latitude, long longitude, long dateOfTheCreationAdvert,
                    long dateOfTheUpdateAdvert)
    {
        id = 0;
        this.price = price;
        this.surfaceArea = surfaceArea;
        this.numberOfRoom = numberOfRoom;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateOfTheCreationAdvert = dateOfTheCreationAdvert;
        this.dateOfTheUpdateAdvert = dateOfTheUpdateAdvert;
    }

}
