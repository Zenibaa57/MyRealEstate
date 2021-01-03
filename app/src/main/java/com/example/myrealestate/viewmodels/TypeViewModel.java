package com.example.myrealestate.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myrealestate.models.Type;
import com.example.myrealestate.repository.RealEstateRepository;

import java.util.logging.Handler;

public class TypeViewModel extends AndroidViewModel {

    //Design patter MVVM
    public TypeViewModel(@NonNull Application application) {
        super(application);

    }

}
