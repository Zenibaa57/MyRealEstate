package com.example.myrealestate.exchangeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IExchangeAPI {

    //Requêtes API
    @GET("latest?")
    Call<UsdToEur> getUsdToEur(@Query("base") String base);
}
