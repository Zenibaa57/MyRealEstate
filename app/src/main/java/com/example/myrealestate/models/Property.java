package com.example.myrealestate.models;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.myrealestate.repository.RealEstateRepository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

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
                        childColumns = "agentId"),},
        indices = {@Index(value = "typeId" ),
                @Index(value = "propertyStatusId"),
                @Index(value = "agentId")
        })

public class Property implements Serializable {

    @PrimaryKey(autoGenerate = true) public int id;
    public double price;
    public double surfaceArea;
    public int numberOfRoom;
    public String description;
    public String address;;
    public double latitude;;
    public double longitude;;
    public long dateOfTheCreationAdvert;
    public long dateOfTheUpdateAdvert;
    public int typeId;
    public int propertyStatusId;
    public int agentId;

    public Property(double price, double surfaceArea, int numberOfRoom, String description, String address,
                    double latitude, double longitude, long dateOfTheCreationAdvert, long dateOfTheUpdateAdvert,
                    int typeId, int propertyStatusId, int agentId) {

        this.id = 0;
        this.price = price;
        this.surfaceArea = surfaceArea;
        this.numberOfRoom = numberOfRoom;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateOfTheCreationAdvert = dateOfTheCreationAdvert;
        this.dateOfTheUpdateAdvert = dateOfTheUpdateAdvert;
        this.typeId = typeId;
        this.propertyStatusId = propertyStatusId;
        this.agentId = agentId;
    }

    public int getPlace(Context context) {
        String type = RealEstateRepository.getInstance(context).getTypeById(typeId);
        return context.getResources().getIdentifier(type.toLowerCase(), "drawable", context.getPackageName());
    }

    public String buildPropertyName(Context context) {
        String propertyName = "#" + id +" " + sizeName()+" " + RealEstateRepository.getInstance(context).getTypeById(typeId).toLowerCase();
        return propertyName;
    }

    private String sizeName(){
        String resulat="";

        if (surfaceArea<30){
            resulat="Tiny";
        }else if(surfaceArea>30 && surfaceArea<80) {
            resulat = "Little";
        }else if(surfaceArea>80 && surfaceArea<120){
            resulat="Modest";
        }else if(surfaceArea>120 && surfaceArea<200){
            resulat="Big";
        }else if(surfaceArea>200){
            resulat="Giant";
        }
        return resulat;
    }

}
