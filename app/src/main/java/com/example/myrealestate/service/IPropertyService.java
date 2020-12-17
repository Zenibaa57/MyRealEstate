package com.example.myrealestate.service;

import androidx.lifecycle.LiveData;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;

import java.util.List;

public interface IPropertyService {

    //Interface, contrat défini
    LiveData<List<Property>> getProperty();

    void addProperty(Property property);


}
