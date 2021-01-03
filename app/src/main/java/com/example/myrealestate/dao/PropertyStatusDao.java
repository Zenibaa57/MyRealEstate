package com.example.myrealestate.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.myrealestate.service.IPropertyService;
import com.example.myrealestate.service.IPropertyStatusService;

@Dao
public interface PropertyStatusDao extends IPropertyStatusService {

    //Requêtes de la BDD, interface (contrat)

    @Query("SELECT id FROM PropertyStatus WHERE isAvaible = :status") int getStatusByAvailability (boolean status);

    @Override
    @Query("SELECT isAvaible FROM PropertyStatus WHERE id = :id") boolean getStatusById(int id);
}
