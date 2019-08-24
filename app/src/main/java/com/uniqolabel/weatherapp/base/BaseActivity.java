package com.uniqolabel.weatherapp.base;

import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.uniqolabel.weatherapp.R;
import com.uniqolabel.weatherapp.utils.Utils;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    public void setupToolbar(@Nullable String title, boolean homeButtonEnable) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }

        if (!homeButtonEnable) {
            int inset = (int) Utils.dpToPx(this, 16);
            toolbar.setContentInsetsAbsolute(inset, inset);
        }

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(homeButtonEnable);
            actionBar.setDisplayShowHomeEnabled(homeButtonEnable);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    // Fragment Related
    protected void replaceFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    protected BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() == null || !getCurrentFragment().onBackPressed()) {
            super.onBackPressed();
        }
    }

}
