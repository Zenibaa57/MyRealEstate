package com.example.myrealestate.exchangeAPI;

import android.content.Context;

import com.example.myrealestate.repository.RealEstateRepository;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static volatile Retrofit instance;

    public Retrofit(Context context) {
    }

    public static Retrofit getInstance(Context context) {
        if (instance == null) {
            synchronized (RealEstateRepository.class) {
                if (instance == null)
                    instance = new Retrofit(context);
            }
        }
        return instance;
    }

    private Retrofit() {
    }

    //Définition du convertisseur JSON
    private static retrofit2.Retrofit retrofit = null;

    public static retrofit2.Retrofit getclient(OkHttpClient okHttpClient){

        if (retrofit == null){
            retrofit = new  retrofit2.Retrofit.Builder()
                    .baseUrl("https://api.exchangeratesapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
