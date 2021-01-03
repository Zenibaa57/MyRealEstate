package com.example.myrealestate.exchangeAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsdToEur {


     //Serealization JSOn to object

    @SerializedName("rates")
    @Expose
    public Rates rates;

    public class Rates {

        @SerializedName("EUR")
        @Expose
        public String EurRates;
    }
}
