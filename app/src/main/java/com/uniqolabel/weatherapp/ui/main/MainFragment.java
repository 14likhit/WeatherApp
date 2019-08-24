package com.uniqolabel.weatherapp.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniqolabel.weatherapp.R;
import com.uniqolabel.weatherapp.base.BaseFragment;
import com.uniqolabel.weatherapp.data.model.ForecastResponse;
import com.uniqolabel.weatherapp.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment implements MainContract.View {

    public static final String TAG = "MainFragment";

    private MainPresenter presenter;

    private FragmentMainBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        presenter.getWeatherForecast();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onWeatherForecastReceived(ForecastResponse forecastResponse) {
        binding.latitude.setText(forecastResponse.getLatitude().toString());
        binding.longitude.setText(forecastResponse.getLongitude().toString());
        binding.timezone.setText(forecastResponse.getTimezone());
        binding.summary.setText(forecastResponse.getDaily().getSummary());
        binding.rootContainer.setVisibility(View.VISIBLE);
    }
}
