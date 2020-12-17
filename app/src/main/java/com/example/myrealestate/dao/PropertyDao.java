package com.example.myrealestate.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.service.IPropertyService;

import java.util.List;

@Dao
public interface PropertyDao extends IPropertyService {

    //Requêtes de la BDD, interface (contrat)

    @Query("SELECT * FROM Agent")
    LiveData<List<Property>> getProperty();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProperty(Property property);

}
