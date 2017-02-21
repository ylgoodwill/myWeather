package com.android.yz.myweather.database;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/2/16.
 */

public class City extends DataSupport {
    private  int id;
    private  String name;
    private  int provinceId;

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    private int cityCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return name;
    }

    public void setCityName(String cityName) {
        this.name = cityName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
