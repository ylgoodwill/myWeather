package com.android.yz.myweather.util;

import android.text.TextUtils;

import com.android.yz.myweather.database.City;
import com.android.yz.myweather.database.Country;
import com.android.yz.myweather.database.Province;
import com.android.yz.myweather.gson.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class HandleDataFroService {
    public static boolean handleProvinceData(String response) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            List<Province> provinces = gson.fromJson(response, new TypeToken<List<Province>>() {
            }.getType());
            for (Province province : provinces) {
                Province p = new Province();
                p.setName(province.getName());
                p.setId(province.getId());
                p.save();
            }
            return true;
        }

        return false;
    }

    public static boolean handleCityData(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            List<City> citys = gson.fromJson(response, new TypeToken<List<City>>() {
            }.getType());
            for (City city : citys) {
                City c = new City();
                c.setCityCode(city.getId());
                c.setCityName(city.getCityName());
                c.setProvinceId(provinceId);
                c.save();
            }
            return true;
        }

        return false;
    }

    public static boolean handleCountryData(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            List<Country> countries = gson.fromJson(response, new TypeToken<List<Country>>() {
            }.getType());
            for (Country country : countries) {
                Country cc = new Country();
                cc.setCityId(cityId);
                cc.setName(country.getName());
                cc.setWeather_id(country.getWeather_id());
                cc.save();
            }
            return true;
        }
        return false;
    }

    public static Weather handleWeatherData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String content = jsonArray.getJSONObject(0).toString();
            Weather weather = new Gson().fromJson(content, Weather.class);
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
