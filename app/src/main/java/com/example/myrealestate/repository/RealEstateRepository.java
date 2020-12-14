package com.example.myrealestate.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

    private RealEstateRepository(Context context) {

        myRealEstateDatabase = Room.databaseBuilder(context, MyRealEstateDatabase.class,"RealEstate-database").allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Elisabeth','HARMON','female','Business Woman');");
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Alexandre','BRANCOLINI','male','Business Man');");
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Ludovic','ROLAND','male','Business Man');");
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Mohammed','CHAKOUCH','male','Business Man');");
            }
        }).build();
    }

    public void addProperty(Property property)
    {
        myRealEstateDatabase.propertyDao().addProperty(property);
    }

    public LiveData<List<Agent>> getAgent()
    {
        return myRealEstateDatabase.agentDao().getAgent();
    }

    public void addAgent(Agent agent)
    {
        myRealEstateDatabase.agentDao().addAgent(agent);
    }



}
