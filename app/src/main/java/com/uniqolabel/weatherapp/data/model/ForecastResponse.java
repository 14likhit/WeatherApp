package com.uniqolabel.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

public class ForecastResponse {

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("daily")
    private DayForecast daily;

    @SerializedName("offset")
    private Double offset;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public DayForecast getDaily() {
        return daily;
    }

    public void setDaily(DayForecast daily) {
        this.daily = daily;
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

}
