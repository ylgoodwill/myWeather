package com.android.yz.myweather.database;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/2/16.
 */

public class Province extends DataSupport {
    private  int id;
    private  String name;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
