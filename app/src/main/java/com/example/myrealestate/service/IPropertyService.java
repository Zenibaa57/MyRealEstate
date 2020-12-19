package com.example.myrealestate.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;

import java.util.List;

public interface IPropertyService {

    //Interface, contrat défini
    LiveData<List<Property>> getProperty();

    LiveData<Property> getPropertyById(int id);

    void addProperty(Property property);

    void deletePropertyById(int id);

}
