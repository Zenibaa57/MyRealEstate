package com.example.myrealestate.service;

import androidx.lifecycle.LiveData;

import com.example.myrealestate.models.Type;

public interface ITypeService {

    //Interface, contrat défini
    int getTypeByName(String type);

    String getTypeById(int id);
}
