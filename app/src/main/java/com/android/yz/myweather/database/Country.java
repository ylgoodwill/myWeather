package com.android.yz.myweather.database;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/2/16.
 */

public class Country extends DataSupport {
    private int id;
    private String name;

    public String getWeather_id() {
        return weather_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    private String weather_id;
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
