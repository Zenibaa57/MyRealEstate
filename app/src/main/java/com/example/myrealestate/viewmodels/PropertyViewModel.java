package com.example.myrealestate.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myrealestate.models.Property;

import java.util.List;

public class PropertyViewModel extends ViewModel {

    private MutableLiveData<List<Property>> vmProperty;

    public  void init(){
    }

    public LiveData<List<Property>> getProperty(){
        return vmProperty;
    }
}
