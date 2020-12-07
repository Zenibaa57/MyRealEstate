package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Property implements Serializable {

    @PrimaryKey(autoGenerate = true) public int id;
    public String type;
    public String price;
    public String surfaceArea;
    public String numberOfRoom;
    public String description;
    public String address;;
    public String latitude;;
    public String longitude;;
    public String dateOfTheCreationAdvert;
    public String dateOfTheUpdateAdvert;
    public String status;
    public String nameAgent;

    public Property(String type, String price, String surfaceArea, String numberOfRoom, String description,
                    String address, String latitude, String longitude, String dateOfTheCreationAdvert,
                    String dateOfTheUpdateAdvert, String status, String nameAgent)
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
