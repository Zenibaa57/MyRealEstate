package com.example.myrealestate.exchangeAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsdToEur {

    @SerializedName("rates")
    @Expose
    public Rates rates;

    public class Rates {

        @SerializedName("EUR")
        @Expose
        public String EurRates;
    }
}
