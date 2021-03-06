package com.example.myrealestate.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myrealestate.models.Property;
import com.example.myrealestate.repository.RealEstateRepository;

import java.util.List;

public class PropertyViewModel extends AndroidViewModel {

    //Design patter MVVM
    public PropertyViewModel(@NonNull Application application) {
        super(application);
    }

    public final LiveData<List<Property>> property = RealEstateRepository.getInstance(getApplication()).getProperty();

    public LiveData<Property> propertyId;


    public LiveData<Property> getPropertyInformation(int id){
       return propertyId = RealEstateRepository.getInstance(getApplication()).getPropertyById(id);
    }
}
