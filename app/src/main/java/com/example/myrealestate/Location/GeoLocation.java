package com.example.myrealestate.Location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;

public class GeoLocation {

    public static ArrayList getAddress (String locationAdress, Context context){

        ArrayList location = new ArrayList();
        Thread thread = new Thread(){
            @Override
            public void run(){
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                try {
                    List addressList = geocoder.getFromLocationName(locationAdress,1);
                    if (addressList != null && addressList.size() > 0){
                        Address address = (Address) addressList.get(0);
                        double latitude = address.getLatitude();
                        double longitude = address.getLongitude();
                        location.add(latitude);
                        location.add(longitude);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        return location;
    }

}
