package com.example.myrealestate.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.repository.RealEstateRepository;

import java.util.List;

public class AgentViewModel extends AndroidViewModel {

    //Design patter MVVM
    public final LiveData<List<Agent>> agents = RealEstateRepository.getInstance(getApplication()).getAgent();

    public AgentViewModel(@NonNull Application application) {
        super(application);
    }

}
