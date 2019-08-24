package com.uniqolabel.weatherapp.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniqolabel.weatherapp.R;
import com.uniqolabel.weatherapp.base.BaseFragment;
import com.uniqolabel.weatherapp.data.model.ForecastResponse;
import com.uniqolabel.weatherapp.databinding.FragmentMainBinding;
import com.uniqolabel.weatherapp.utils.LocationHelper;
import com.uniqolabel.weatherapp.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
        locationHelper.getIsLocationAvailable().observe(this, aBoolean -> {
            if (aBoolean && Utils.isNetworkAvailable(getBaseActivity())) {
                location = locationHelper.getCurrentLocation();
                presenter.getWeatherForecast(location);
            } else {
                if (!Utils.isNetworkAvailable(getBaseActivity())) {
                    showMessage(getString(R.string.network_error_string));
                } else {
                    showMessage(getString(R.string.location_error_string));
                }
                binding.rootContainer.setVisibility(View.GONE);
                binding.retryLayout.setVisibility(View.VISIBLE);
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

        binding.btnRetry.setOnClickListener(v -> locationHelper.startLocationUpdates());

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onWeatherForecastReceived(ForecastResponse forecastResponse) {
        if (forecastResponse != null) {
            binding.retryLayout.setVisibility(View.GONE);
            this.forecastResponse = forecastResponse;
            if (getGeoCodedLocation() != null) {
                binding.city.setText(getGeoCodedLocation().getLocality() + " , " + getGeoCodedLocation().getAdminArea());
            }
            binding.latitudeLongitude.setText(forecastResponse.getLatitude().toString() + " , " + forecastResponse.getLongitude().toString());
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
        } else {
            showMessage(getString(R.string.generic_error_string));
            binding.rootContainer.setVisibility(View.GONE);
            binding.retryLayout.setVisibility(View.VISIBLE);
        }
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

    private Address getGeoCodedLocation() {
        Geocoder geocoder = new Geocoder(getBaseActivity(), Locale.ENGLISH);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null) {
            return addresses.get(0);
        } else {
            return null;
        }
    }

}
