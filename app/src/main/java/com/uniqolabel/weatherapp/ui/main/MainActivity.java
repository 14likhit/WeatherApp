package com.uniqolabel.weatherapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uniqolabel.weatherapp.R;
import com.uniqolabel.weatherapp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar(getString(R.string.app_name), false);
        replaceFragment(MainFragment.newInstance(), MainFragment.TAG, false);

    }
}
