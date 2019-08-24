package com.uniqolabel.weatherapp.ui.main;

import com.uniqolabel.weatherapp.base.BaseView;
import com.uniqolabel.weatherapp.data.model.ForecastResponse;

public interface MainContract {

    interface Presenter {
        void getWeatherForecast();
    }

    interface View extends BaseView {
        void onWeatherForecastReceived(ForecastResponse forecastResponse);
    }

}
