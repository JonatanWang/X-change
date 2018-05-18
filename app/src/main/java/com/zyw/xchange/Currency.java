package com.zyw.xchange;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Zhengyu Wang on 2016-11-14.
 * Email: zhengyuw@kth.se
 * The class of currency.
 */

public class Currency implements Serializable {

    private String name;
    private String rate;
    private Date time;

    public Currency() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "time: " + getTime() + " currency: " + getName() + " rate: " + getRate() ;
    }
}
