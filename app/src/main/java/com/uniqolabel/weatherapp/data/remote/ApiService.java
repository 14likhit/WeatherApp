package com.uniqolabel.weatherapp.data.remote;


import com.uniqolabel.weatherapp.data.model.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("forecast/{api_key}/{lat},{longi}")
    Call<ForecastResponse> getForecast(@Path("api_key") String api_key, @Path("lat") Double lat, @Path("longi") Double longi, @Query("exclude") String exclude_param);
}

