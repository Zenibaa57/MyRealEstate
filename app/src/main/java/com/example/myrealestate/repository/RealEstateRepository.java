package com.example.myrealestate.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myrealestate.database.MyRealEstateDatabase;
import com.example.myrealestate.models.Agent;
import com.example.myrealestate.models.Property;
import com.example.myrealestate.models.Type;

import java.util.List;
import java.util.Map;

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

        //Initialisation de la base

        myRealEstateDatabase = Room.databaseBuilder(context, MyRealEstateDatabase.class,"RealEstate-database").allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Elisabeth','HARMON','female','Business Woman');");
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Alexandre','BRANCOLINI','male','Business Man');");
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Ludovic','ROLAND','male','Chief Executive Officer');");
                db.execSQL("INSERT Into Agent ('firstname','lastname','sexe','job') VALUES ('Mohammed','CHAKOUCH','male','Business Man');");

                db.execSQL("INSERT Into PropertyStatus ('isAvaible') VALUES (1);");
                db.execSQL("INSERT Into PropertyStatus ('isAvaible') VALUES (0);");

                db.execSQL("INSERT Into Type ('type') VALUES ('Garage');");
                db.execSQL("INSERT Into Type ('type') VALUES ('House');");
                db.execSQL("INSERT Into Type ('type') VALUES ('Building');");
            }
        }).build();
    }


    public LiveData<List<Agent>> getAgent() {
        return myRealEstateDatabase.agentDao().getAgent();
    }

    public String getAgentNameById(int id) {
        return myRealEstateDatabase.agentDao().getAgentNameById(id);
    }

    public int getTypeByName(String type) {
        return myRealEstateDatabase.typeDao().getTypeByName(type);
    }

    public String getTypeById(int id) {
        return myRealEstateDatabase.typeDao().getTypeById(id);
    }

    public int getAgentNameByName(String firstname) {
        return myRealEstateDatabase.agentDao().getAgentNameByName(firstname);
    }

    public int getStatusByAvailability(boolean status) {
        return myRealEstateDatabase.propertyStatusDao().getStatusByAvailability(status);
    }

    public  LiveData<List<Property>> getProperty() {
        return myRealEstateDatabase.propertyDao().getProperty();
    }

    public LiveData<Property> getPropertyById(int id) {
        return myRealEstateDatabase.propertyDao().getPropertyById(id);
    }

    public void addProperty(Property property) {
        myRealEstateDatabase.propertyDao().addProperty(property);
    }


    public void deletePropertyById(int id ) {
         myRealEstateDatabase.propertyDao().deletePropertyById(id);
    }

    public void updateProperty(Map<String, Object> map ) {
        myRealEstateDatabase.propertyDao().updateProperty((Double) map.get("price"),(Double)map.get("surfaceArea"),(Integer) map.get("numberOfRoom"),
                (String) map.get("description"),  (String) map.get("address"),(Double)map.get("latitude"),(Double)map.get("longitude")
                , (Long) map.get("dateOfTheUpdateAdvert"),(Integer) map.get("type"),(Integer)map.get("status")
                ,(Integer)map.get("agentName"),(Integer)map.get("id"));
    }

    public  boolean getStatusById(int id) {
        return myRealEstateDatabase.propertyStatusDao().getStatusById(id);
    }

}
