package com.zyw.xchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhengyu Wang on 2016-11-16.
 * Email: zhengyuw@kth.se
 * Change the original data format into user friendly format "Week Month Data Year"
 */

public class DateFormatConverter {

    private Date date;
    public DateFormatConverter(Date date) {
        this.date = date;
    }

   public String convertDate() {

       String[] str = date.toString().split(" ");
       String formatDate =  str[1] + " " + str[2] + " " + str[5]; //"Week Month Data Year"
       return formatDate;
   }
}
