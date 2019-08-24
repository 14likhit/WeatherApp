package com.uniqolabel.weatherapp.ui.main;

import com.uniqolabel.weatherapp.base.BaseView;

public interface MainContract {

    interface Presenter {
        void getWeatherForecast();
    }

    interface View extends BaseView {
        void onWeatherForecastReceived();
    }

}
