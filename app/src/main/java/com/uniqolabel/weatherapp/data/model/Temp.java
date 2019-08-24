package com.uniqolabel.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Temp {

    @SerializedName("day")
    private Double day;

    @SerializedName("min")
    private Double min;

    @SerializedName("max")
    private Double max;

    @SerializedName("night")
    private Double night;

    @SerializedName("eve")
    private Double eve;

    @SerializedName("morn")
    private Double morn;

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }


}
