package com.example.myrealestate.service;

import androidx.lifecycle.LiveData;
import com.example.myrealestate.models.Agent;

import java.util.List;

public interface IAgentService {

    LiveData<List<Agent>> getAgent();

    int getAgentNameByName(String firstname);

    String getAgentNameById(int id);

}
