package com.android.yz.myweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.android.yz.myweather.gson.Weather;
import com.android.yz.myweather.util.HandleDataFroService;
import com.android.yz.myweather.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.os.SystemClock.elapsedRealtime;

public class UpdateService extends Service {
    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updatePic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
       // int time = 8 * 3600 * 1000;
        int time = 6 * 1000;
        long time1 = SystemClock.elapsedRealtime() + time;
        Intent i = new Intent(this, UpdateService.class);
        PendingIntent p = PendingIntent.getService(UpdateService.this, 0, i, 0);
        manager.cancel(p);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time1, p);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updatePic() {

        String urlPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(urlPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String pic = response.body().string();
                PreferenceManager.getDefaultSharedPreferences(UpdateService.this).edit().putString("piccache", pic).apply();
            }
        });

    }

    private void updateWeather() {
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(UpdateService.this);
        String xxweather = share.getString("weathercache", null);
        if (xxweather != null) {
            Weather w = HandleDataFroService.handleWeatherData(xxweather);
            String id = w.basic.weatherId;
            String url = "http://guolin.tech/api/weather?cityid=" + id + "&key=888969ca66e143c684c19f8dee814f32";
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    Weather w = HandleDataFroService.handleWeatherData(res);
                    if (w != null && "ok".equals(w.status)) {
                        PreferenceManager.getDefaultSharedPreferences(UpdateService.this).edit().putString("weathercache", res).apply();
                    }

                }
            });
        }

    }
}
