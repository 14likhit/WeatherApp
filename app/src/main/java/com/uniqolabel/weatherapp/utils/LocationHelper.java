package com.uniqolabel.weatherapp.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.uniqolabel.weatherapp.R;


public class LocationHelper extends LocationCallback {

    private static final String TAG = "LocationHelper";

    public static final int REQUEST_CHECK_SETTING = 101;

    private static final long DEFAULT_UPDATE_INTERVAL = 20000; // 10 seconds
    private static final int DEFAULT_PRIORITY = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;

    private static LocationHelper locationHelper;

    private Activity activity;
    private FusedLocationProviderClient fusedLocationClient;
    private SettingsClient settingsClient;
    private LocationRequest locationRequest;

    private AlertDialog alertDialog = null;

    private boolean isLocationRequestActive;
    private MutableLiveData<Boolean> isLocationAvailable = new MutableLiveData<>();

    private Address address = null;
    private Gson gson = new Gson();
    private Location currentLocation;

    private Long lastFetchedLocationTime = null;

    private LocationHelper(Activity activity) {
        this.activity = activity;

        initClients();
        initLocationRequest();
    }

    public static LocationHelper getInstance(Activity activity) {
        if (locationHelper == null) {
            locationHelper = new LocationHelper(activity);
        }
        return locationHelper;
    }

    private void initClients() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        settingsClient = LocationServices.getSettingsClient(activity);
    }

    private void initLocationRequest() {
        // Create the location request to start receiving updates
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(DEFAULT_PRIORITY);
        locationRequest.setInterval(DEFAULT_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(5000);
    }

    private LocationSettingsRequest getLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        return builder.build();
    }

    ///////////////////////////
    // GPS SETTINGS RELATED ///
    ///////////////////////////

    public void startLocationUpdates() {
        if (isLocationRequestActive) {
            return;
        }
        boolean permissionGrantedAccessFineLocation = Utils.checkPermissionGranted(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        boolean permissionGrantedAccessCoarseLocation = Utils.checkPermissionGranted(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionGrantedAccessFineLocation && permissionGrantedAccessCoarseLocation) {
            checkLocationSettings();
        } else {
            if (alertDialog == null || !alertDialog.isShowing()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void checkLocationSettings() {
        settingsClient.checkLocationSettings(getLocationSettingsRequest())
                .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                        Logger.d(TAG, "Locations All location settings are satisfied.");
                        isLocationRequestActive = true;
                        fusedLocationClient.requestLocationUpdates(locationRequest, LocationHelper.this, Looper.myLooper());
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(activity, REQUEST_CHECK_SETTING);
                                } catch (IntentSender.SendIntentException sie) {
//                                Logger.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
//                            Logger.e(TAG, errorMessage);
                                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        Log.i(TAG, "onLocationResult: Last=" + locationResult.getLastLocation().toString());
//        for (Location location : locationResult.getLocations()) {
//            Logger.i(TAG, "onLocationResult: " + location.toString());
//        }

        // Deliver to listener
        if (locationResult.getLastLocation() != null && locationRequest.getNumUpdates() <= 1) {
            setLastFetchedLocationTime(SystemClock.currentThreadTimeMillis());
            currentLocation = locationResult.getLastLocation();
//            listener.onLocationReceived(currentLocation.getValue());
            stopLocationUpdates();
        }

        if (locationRequest.getNumUpdates() > 1) {
            checkLocationSettings();
        }
    }

    @Override
    public void onLocationAvailability(LocationAvailability locationAvailability) {
//        Logger.d(TAG, "Location availability: " + locationAvailability.isLocationAvailable());
        if (!locationAvailability.isLocationAvailable() && locationRequest.getNumUpdates() > 1) {
            isLocationAvailable.setValue(false);
            currentLocation = null;
//            listener.onLocationNotAvailable();
        }
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.i(TAG, "Got Last Location: " + location);
                        if (location != null) {
                            LocationHelper.this.setLastFetchedLocationTime(SystemClock.currentThreadTimeMillis());
                            currentLocation = location;
                            LocationHelper.this.stopLocationUpdates();
//                            listener.onLocationReceived(currentLocation.getValue());
                        }
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        LocationHelper.this.startLocationUpdates();
                    }
                });
    }

    public void stopLocationUpdates() {
        if (isLocationRequestActive) {
            fusedLocationClient.removeLocationUpdates(this);
            isLocationRequestActive = false;
        }
    }

    ///////////////////////////
    /// LOCATION PERMISSIONS //
    ///////////////////////////

    @SuppressLint("NewApi")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED && !activity.shouldShowRequestPermissionRationale(permissions[i])) {
                showGoToPermissionsSettingDialog();
                break;
            }
        }
    }

    private void showGoToPermissionsSettingDialog() {
        alertDialog = new AlertDialog.Builder(activity)
                .setTitle("Settings")
                .setMessage("Enable Location Permission")
                .setPositiveButton("Goto Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LocationHelper.this.goToPermissionSettings();
                    }
                })
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void goToPermissionSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }



    public void setPriority(int priority) {
        locationRequest.setPriority(priority);
    }

    public void setUpdateInterval(long updateInterval) {
        locationRequest.setInterval(updateInterval);
    }

    public void setNumberOfUpdates(int numUpdates) {
        locationRequest.setNumUpdates(numUpdates != 0 ? numUpdates : Integer.MAX_VALUE);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public MutableLiveData<Boolean> getIsLocationAvailable() {
        return isLocationAvailable;
    }

    public String getAddress() {
        return address != null ? gson.toJson(address) : null;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Long getLastFetchedLocationTime() {
        return lastFetchedLocationTime;
    }

    private void setLastFetchedLocationTime(Long lastFetchedLocationTime) {
        this.lastFetchedLocationTime = lastFetchedLocationTime;
    }
}
