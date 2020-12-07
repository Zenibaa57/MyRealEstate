package com.example.myrealestate.repository;

import android.content.Context;

import com.example.myrealestate.database.MyRealEstateDatabase;
import com.example.myrealestate.models.Property;

import java.util.List;

public final class PropertyRepository {

    private static volatile PropertyRepository instance;
    private MyRealEstateDatabase myRealEstateDatabase;

    public PropertyRepository(Context context) {
    }

    public static PropertyRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (PropertyRepository.class) {
                if (instance == null)
                    instance = new PropertyRepository(context);
            }
        }
        return instance;
    }

    public List<Property> getProperties()
    {
        return myRealEstateDatabase.propertyDao().getProperties();
    }

    public void addProperty(Property property)
    {
        myRealEstateDatabase.propertyDao().addProperty(property);
    }


    public void deleteProperty(Property property)
    {
        myRealEstateDatabase.propertyDao().deleteByName(property.type);
    }

    public void updateProperty(Property property) {
        myRealEstateDatabase.propertyDao().updateProperty();
    }
}
