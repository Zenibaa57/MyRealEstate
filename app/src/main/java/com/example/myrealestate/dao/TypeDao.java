package com.example.myrealestate.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RawQuery;

import com.example.myrealestate.models.Type;
import com.example.myrealestate.service.ITypeService;

@Dao
public interface TypeDao extends ITypeService {

    @Override
    @Query("SELECT id FROM Type WHERE type = :type") int getTypeByName (String type);

    @Override
    @Query("SELECT type FROM Type WHERE id = :id") String getTypeById(int id);
}
