package com.example.myrealestate.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.repository.RealEstateRepository;

import java.util.List;

public class PropertyViewModel extends AndroidViewModel {

    public PropertyViewModel(@NonNull Application application) {
        super(application);
    }

    public final LiveData<List<Property>> property = RealEstateRepository.getInstance(getApplication()).getProperty();

   // public final LiveData<Property> propertyId = RealEstateRepository.getInstance(getApplication()).getPropertyById(id);
}
