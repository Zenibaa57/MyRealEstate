package com.example.myrealestate.repository;

import android.content.Context;

import com.example.myrealestate.models.Property;

import java.util.List;

public final class PropertyRepository {

    private static volatile PropertyRepository instance;

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

//    public List<Property> getProperties()
//    {
//        return cityWeatherDatabases.cityWeatherDao().getCity();
//    }
//
//    public void addCity(Property city)
//    {
//        cityWeatherDatabases.cityWeatherDao().addCity(city);
//    }
//
//
//    public void deleteCity(Property city)
//    {
//        cityWeatherDatabases.cityWeatherDao().deleteByName(city.cityName);
//    }
//
//    public void updateCity(CityWeather city) {
//        cityWeatherDatabases.cityWeatherDao().updateCity(city.cityName,city.temp,city.feelsLike,
//                city.tempMin,city.tempMax,city.icon,city.requestTime);
//    }
}
