package com.example.myrealestate.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.myrealestate.database.MyRealEstateDatabase;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;

import java.util.List;

public final class RealEstateRepository {

    private static volatile RealEstateRepository instance;
    private MyRealEstateDatabase myRealEstateDatabase;

    public static RealEstateRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (RealEstateRepository.class) {
                if (instance == null)
                    instance = new RealEstateRepository(context);
            }
        }
        return instance;
    }

    public RealEstateRepository(Context context) {
        myRealEstateDatabase = Room.databaseBuilder(context, MyRealEstateDatabase.class,"RealEstate-database").allowMainThreadQueries().build();
    }

    public void addProperty(Property property)
    {
        myRealEstateDatabase.propertyDao().addProperty(property);
    }

    public List<Agent> getAgent()
    {
        return myRealEstateDatabase.agentDao().getAgent();
    }

    public void addAgent(Agent agent)
    {
        myRealEstateDatabase.agentDao().addAgent(agent);
    }



}
