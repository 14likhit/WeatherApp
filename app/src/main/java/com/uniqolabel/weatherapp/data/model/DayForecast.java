package com.uniqolabel.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayForecast {

    @SerializedName("summary")
    private String summary;

    @SerializedName("icon")
    private String icon;

    @SerializedName("data")
    private List<ForecastData> data = null;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ForecastData> getData() {
        return data;
    }

    public void setData(List<ForecastData> data) {
        this.data = data;
    }

}
