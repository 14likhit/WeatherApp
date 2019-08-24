package com.uniqolabel.weatherapp.data.model;

import com.google.gson.annotations.SerializedName;

public class ForecastResponse {

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private Integer message;

    @SerializedName("city")
    private City city;

    @SerializedName("cnt")
    private Integer cnt;

    @SerializedName("list")
    private java.util.List<DayForecast> list = null;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<DayForecast> getList() {
        return list;
    }

    public void setList(java.util.List<DayForecast> list) {
        this.list = list;
    }

}
