package com.example.myrealestate.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myrealestate.models.Agent;
import com.example.myrealestate.service.IAgentService;

import java.util.List;

@Dao
public interface AgentDao extends IAgentService {

    //Requêtes de la BDD, interface (contrat)
    @Query("SELECT * FROM Agent")
    LiveData<List<Agent>> getAgent();
}