package com.example.myrealestate.service;

import androidx.lifecycle.LiveData;

import com.example.myrealestate.models.Type;

public interface ITypeService {

    int getTypeByName(String type);
}
