package com.uniqolabel.weatherapp.ui.main;

import com.uniqolabel.weatherapp.base.BasePresenter;
import com.uniqolabel.weatherapp.data.remote.ApiClient;
import com.uniqolabel.weatherapp.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void getWeatherForecast() {
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        final Call<String> request = service.getScan();
    }
}
