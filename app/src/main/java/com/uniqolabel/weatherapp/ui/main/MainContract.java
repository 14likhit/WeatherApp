package com.uniqolabel.weatherapp.ui.main;

import android.location.Location;

import com.uniqolabel.weatherapp.base.BaseView;
import com.uniqolabel.weatherapp.data.model.ForecastResponse;

public interface MainContract {

    interface Presenter {
        void getWeatherForecast(Location location);
    }

    interface View extends BaseView {
        void onWeatherForecastReceived(ForecastResponse forecastResponse);
    }

}
