package com.example.myrealestate.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Property",
        foreignKeys = {
                @ForeignKey(entity = Type.class,
                        parentColumns = "id",
                        childColumns = "typeId"),
                @ForeignKey(entity = PropertyStatus.class,
                        parentColumns = "id",
                        childColumns = "propertyStatusId"),
                @ForeignKey(entity = Agent.class,
                         parentColumns = "id",
                        childColumns = "agentId"),
        })

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
    public int typeId;
    public int propertyStatusId;
    public int agentId;


    public Property(long price, long surfaceArea, int numberOfRoom, String description,
                    String address, long latitude, long longitude, long dateOfTheCreationAdvert,
                    long dateOfTheUpdateAdvert) {
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
