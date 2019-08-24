package com.uniqolabel.weatherapp.ui.main;

import android.util.Log;

import com.uniqolabel.weatherapp.base.BasePresenter;
import com.uniqolabel.weatherapp.data.model.ForecastResponse;
import com.uniqolabel.weatherapp.data.remote.ApiClient;
import com.uniqolabel.weatherapp.data.remote.ApiService;
import com.uniqolabel.weatherapp.utils.Constants;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void getWeatherForecast() {
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        final Call<ForecastResponse> request = service.getForecast(Constants.API_KEY, 12.97, 77.59, "[currently,minutely,hourly,alerts,flags]");
        request.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                Log.e("API", response.body().toString());
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }
}
