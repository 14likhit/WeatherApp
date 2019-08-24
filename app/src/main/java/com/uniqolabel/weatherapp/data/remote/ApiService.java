package com.uniqolabel.weatherapp.data.remote;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/daily")
    Call<String> getScan();
}

