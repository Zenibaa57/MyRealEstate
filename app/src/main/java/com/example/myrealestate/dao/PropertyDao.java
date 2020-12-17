package com.example.myrealestate.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myrealestate.models.Property;
import com.example.myrealestate.service.IPropertyService;

@Dao
public interface PropertyDao extends IPropertyService {

    //Requêtes de la BDD, interface (contrat)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProperty(Property property);

}
