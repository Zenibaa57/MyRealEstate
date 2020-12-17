package com.example.myrealestate.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.myrealestate.service.IPropertyService;

@Dao
public interface PropertyStatusDao extends IPropertyService {

    @Query("SELECT id FROM PropertyStatus WHERE isAvaible = :status") int getStatusByAvailability (boolean status);
}
