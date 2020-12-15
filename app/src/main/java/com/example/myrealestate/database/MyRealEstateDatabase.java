package com.example.myrealestate.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myrealestate.dao.AgentDao;
import com.example.myrealestate.dao.PropertyDao;
import com.example.myrealestate.dao.PropertyStatusDao;
import com.example.myrealestate.dao.TypeDao;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.models.PropertyStatus;
import com.example.myrealestate.models.Type;

@Database(entities = { Property.class, Agent.class, PropertyStatus.class, Type.class}, version = 1, exportSchema = false)
public abstract class MyRealEstateDatabase extends RoomDatabase {
    public abstract PropertyDao propertyDao();
    public abstract AgentDao agentDao();
    public abstract PropertyStatusDao propertyStatusDao();
    public abstract TypeDao typeDao();
    }


