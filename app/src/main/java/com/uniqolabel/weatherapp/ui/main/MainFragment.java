package com.uniqolabel.weatherapp.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniqolabel.weatherapp.R;
import com.uniqolabel.weatherapp.base.BaseFragment;
import com.uniqolabel.weatherapp.data.model.ForecastResponse;
import com.uniqolabel.weatherapp.databinding.FragmentMainBinding;
import com.uniqolabel.weatherapp.utils.LocationHelper;
import com.uniqolabel.weatherapp.utils.Utils;

public class MainFragment extends BaseFragment implements MainContract.View {

    public static final String TAG = "MainFragment";

    private MainPresenter presenter;

    private FragmentMainBinding binding;

    private Location location;
    private LocationHelper locationHelper;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationHelper = LocationHelper.getInstance(getBaseActivity());
        locationHelper.startLocationUpdates();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new MainPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onDetach() {
        presenter.detachView();
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationHelper.getIsLocationAvailable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    location = locationHelper.getCurrentLocation();
                    presenter.getWeatherForecast(location);
                } else {
                    locationHelper.startLocationUpdates();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onWeatherForecastReceived(ForecastResponse forecastResponse) {
        binding.latitude.setText(forecastResponse.getLatitude().toString());
        binding.longitude.setText(forecastResponse.getLongitude().toString());
        binding.date.setText(Utils.getDate(forecastResponse.getDaily().getData().get(0).getTime(), forecastResponse.getTimezone()));
        binding.summary.setText(forecastResponse.getDaily().getSummary());
        binding.mintemp.setText(forecastResponse.getDaily().getData().get(0).getTemperatureMin().toString());
        binding.maxtemp.setText(forecastResponse.getDaily().getData().get(0).getTemperatureMax().toString());
        binding.humidity.setText(forecastResponse.getDaily().getData().get(0).getHumidity().toString());
        binding.pressure.setText(forecastResponse.getDaily().getData().get(0).getPressure().toString());
        binding.sunrise.setText(Utils.getTime(forecastResponse.getDaily().getData().get(0).getSunriseTime(), forecastResponse.getTimezone()));
        binding.sunset.setText(Utils.getTime(forecastResponse.getDaily().getData().get(0).getSunsetTime(), forecastResponse.getTimezone()));
        binding.day1.setText(Utils.getDate(forecastResponse.getDaily().getData().get(0).getTime(), forecastResponse.getTimezone()));
        binding.day2.setText(Utils.getDate(forecastResponse.getDaily().getData().get(1).getTime(), forecastResponse.getTimezone()));
        binding.day3.setText(Utils.getDate(forecastResponse.getDaily().getData().get(2).getTime(), forecastResponse.getTimezone()));
        binding.day4.setText(Utils.getDate(forecastResponse.getDaily().getData().get(3).getTime(), forecastResponse.getTimezone()));
        binding.day5.setText(Utils.getDate(forecastResponse.getDaily().getData().get(4).getTime(), forecastResponse.getTimezone()));
        binding.day6.setText(Utils.getDate(forecastResponse.getDaily().getData().get(5).getTime(), forecastResponse.getTimezone()));
        binding.day7.setText(Utils.getDate(forecastResponse.getDaily().getData().get(6).getTime(), forecastResponse.getTimezone()));
        binding.day8.setText(Utils.getDate(forecastResponse.getDaily().getData().get(7).getTime(), forecastResponse.getTimezone()));
        binding.rootContainer.setVisibility(View.VISIBLE);
    }

}
