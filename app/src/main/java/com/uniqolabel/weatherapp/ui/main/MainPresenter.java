package com.uniqolabel.weatherapp.ui.main;

import android.location.Location;
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
    public void getWeatherForecast(Location location) {
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        final Call<ForecastResponse> request = service.getForecast(Constants.API_KEY, location.getLatitude(), location.getLongitude(), "[currently,minutely,hourly,alerts,flags]");
        //noinspection NullableProblems
        request.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                Log.e("API", response.body().toString());
                if (response.body() != null) {
                    getView().onWeatherForecastReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e("API", t.getMessage());
                getView().showMessage("Unable to get Forecast");
            }
        });
    }
}
