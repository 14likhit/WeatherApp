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

    private ForecastResponse forecastResponse;

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

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        binding.day1.setOnClickListener(v -> updateForecastUI(forecastResponse, 0));
        binding.day2.setOnClickListener(v -> updateForecastUI(forecastResponse, 1));
        binding.day3.setOnClickListener(v -> updateForecastUI(forecastResponse, 2));
        binding.day4.setOnClickListener(v -> updateForecastUI(forecastResponse, 3));
        binding.day5.setOnClickListener(v -> updateForecastUI(forecastResponse, 4));
        binding.day6.setOnClickListener(v -> updateForecastUI(forecastResponse, 5));
        binding.day7.setOnClickListener(v -> updateForecastUI(forecastResponse, 6));
        binding.day8.setOnClickListener(v -> updateForecastUI(forecastResponse, 7));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onWeatherForecastReceived(ForecastResponse forecastResponse) {
        this.forecastResponse = forecastResponse;
        binding.latitude.setText(forecastResponse.getLatitude().toString());
        binding.longitude.setText(forecastResponse.getLongitude().toString());
        binding.summary.setText(forecastResponse.getDaily().getSummary());
        updateForecastUI(forecastResponse, 0);
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

    @SuppressLint("SetTextI18n")
    private void updateForecastUI(ForecastResponse forecastResponse, int day) {
        binding.date.setText(Utils.getDate(forecastResponse.getDaily().getData().get(day).getTime(), forecastResponse.getTimezone()));
        binding.temp.setText(forecastResponse.getDaily().getData().get(day).getSummary());
        binding.mintemp.setText(forecastResponse.getDaily().getData().get(day).getTemperatureMin().toString());
        binding.maxtemp.setText(forecastResponse.getDaily().getData().get(day).getTemperatureMax().toString());
        binding.humidity.setText(forecastResponse.getDaily().getData().get(day).getHumidity().toString());
        binding.pressure.setText(forecastResponse.getDaily().getData().get(day).getPressure().toString());
        binding.sunrise.setText(Utils.getTime(forecastResponse.getDaily().getData().get(day).getSunriseTime(), forecastResponse.getTimezone()));
        binding.sunset.setText(Utils.getTime(forecastResponse.getDaily().getData().get(day).getSunsetTime(), forecastResponse.getTimezone()));

    }

}
