package com.example.myrealestate.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.service.IPropertyService;

import java.util.List;

@Dao
public interface PropertyDao extends IPropertyService {

    //Requêtes de la BDD, interface (contrat)

    @Query("SELECT * FROM Property")
    LiveData<List<Property>> getProperty();

    @Query("SELECT * FROM Property WHERE id = :id")
    LiveData<Property> getPropertyById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProperty(Property property);


    @Query("DELETE FROM Property WHERE id = :id")
    void deletePropertyById (int id);

    @Query("UPDATE Property SET `price` = :price, `surfaceArea`= :surfaceArea,`numberOfRoom` = :numberOfRoom, `description`= :description," +
            "`address` = :address, `latitude`= :latitude, `longitude`= :longitude, `dateOfTheUpdateAdvert`= :dateOfTheUpdateAdvert," +
            " `typeId`= :typeId, `propertyStatusId`= :propertyStatusId, `agentId`= :agentId WHERE id = :id")
    void updateProperty(double price,double surfaceArea, int numberOfRoom,String description,String address, double latitude, double longitude,
                      long dateOfTheUpdateAdvert, int typeId, int propertyStatusId,int agentId, int id);
}
