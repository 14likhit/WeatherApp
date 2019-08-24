package com.uniqolabel.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

public class ForecastData {

    @SerializedName("time")
    private Long time;

    @SerializedName("summary")
    private String summary;

    @SerializedName("icon")
    private String icon;

    @SerializedName("sunriseTime")
    private Long sunriseTime;

    @SerializedName("sunsetTime")
    private Long sunsetTime;

    @SerializedName("moonPhase")
    private Double moonPhase;

    @SerializedName("precipIntensity")
    private Double precipIntensity;

    @SerializedName("precipIntensityMax")
    private Double precipIntensityMax;

    @SerializedName("precipIntensityMaxTime")
    private Long precipIntensityMaxTime;

    @SerializedName("precipProbability")
    private Double precipProbability;

    @SerializedName("precipType")
    private String precipType;

    @SerializedName("temperatureHigh")
    private Double temperatureHigh;

    @SerializedName("temperatureHighTime")
    private Long temperatureHighTime;

    @SerializedName("temperatureLow")
    private Double temperatureLow;

    @SerializedName("temperatureLowTime")
    private Long temperatureLowTime;

    @SerializedName("apparentTemperatureHigh")
    private Double apparentTemperatureHigh;

    @SerializedName("apparentTemperatureHighTime")
    private Long apparentTemperatureHighTime;

    @SerializedName("apparentTemperatureLow")
    private Double apparentTemperatureLow;

    @SerializedName("apparentTemperatureLowTime")
    private Long apparentTemperatureLowTime;

    @SerializedName("dewPoint")
    private Double dewPoint;

    @SerializedName("humidity")
    private Double humidity;

    @SerializedName("pressure")
    private Double pressure;

    @SerializedName("windSpeed")
    private Double windSpeed;

    @SerializedName("windGust")
    private Double windGust;

    @SerializedName("windGustTime")
    private Long windGustTime;

    @SerializedName("windBearing")
    private Long windBearing;

    @SerializedName("cloudCover")
    private Double cloudCover;

    @SerializedName("uvIndex")
    private Long uvIndex;

    @SerializedName("uvIndexTime")
    private Long uvIndexTime;

    @SerializedName("visibility")
    private Double visibility;

    @SerializedName("ozone")
    private Double ozone;

    @SerializedName("temperatureMin")
    private Double temperatureMin;

    @SerializedName("temperatureMinTime")
    private Long temperatureMinTime;

    @SerializedName("temperatureMax")
    private Double temperatureMax;

    @SerializedName("temperatureMaxTime")
    private Long temperatureMaxTime;

    @SerializedName("apparentTemperatureMin")
    private Double apparentTemperatureMin;

    @SerializedName("apparentTemperatureMinTime")
    private Long apparentTemperatureMinTime;

    @SerializedName("apparentTemperatureMax")
    private Double apparentTemperatureMax;

    @SerializedName("apparentTemperatureMaxTime")
    private Long apparentTemperatureMaxTime;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

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

    public Long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public Long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    public Long getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(Long precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public Long getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public void setTemperatureHighTime(Long temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public Long getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public void setTemperatureLowTime(Long temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
    }

    public Double getApparentTemperatureHigh() {
        return apparentTemperatureHigh;
    }

    public void setApparentTemperatureHigh(Double apparentTemperatureHigh) {
        this.apparentTemperatureHigh = apparentTemperatureHigh;
    }

    public Long getApparentTemperatureHighTime() {
        return apparentTemperatureHighTime;
    }

    public void setApparentTemperatureHighTime(Long apparentTemperatureHighTime) {
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
    }

    public Double getApparentTemperatureLow() {
        return apparentTemperatureLow;
    }

    public void setApparentTemperatureLow(Double apparentTemperatureLow) {
        this.apparentTemperatureLow = apparentTemperatureLow;
    }

    public Long getApparentTemperatureLowTime() {
        return apparentTemperatureLowTime;
    }

    public void setApparentTemperatureLowTime(Long apparentTemperatureLowTime) {
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public Long getWindGustTime() {
        return windGustTime;
    }

    public void setWindGustTime(Long windGustTime) {
        this.windGustTime = windGustTime;
    }

    public Long getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Long windBearing) {
        this.windBearing = windBearing;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Long getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Long uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Long getUvIndexTime() {
        return uvIndexTime;
    }

    public void setUvIndexTime(Long uvIndexTime) {
        this.uvIndexTime = uvIndexTime;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Long getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public void setTemperatureMinTime(Long temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Long getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public void setTemperatureMaxTime(Long temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    public Long getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public void setApparentTemperatureMinTime(Long apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    public Long getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public void setApparentTemperatureMaxTime(Long apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }


}
