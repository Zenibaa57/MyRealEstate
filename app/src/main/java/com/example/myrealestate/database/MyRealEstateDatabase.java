package com.example.myrealestate.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myrealestate.dao.AgentDao;
import com.example.myrealestate.dao.PropertyDao;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;

@Database(entities = { Property.class, Agent.class}, version = 1)
public abstract class MyRealEstateDatabase extends RoomDatabase {
    public abstract PropertyDao propertyDao();
    public abstract AgentDao agentDao();
    }


