package com.zyw.xchange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by Zhengyu Wang on 2016-11-15.
 * Email: zhengyuw@kth.se
 * This class checks if the current date is later than the date saved in the local file, and inform user that online mode is activated.
 * If yes, try to update the local file.
 * If no, inform user that off-line mode is activated.
 */

public class DateValidator {

    public Date getCurrentDate() {

        // Define the data format used
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Date --> String
            // Get the current time.
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String dateStringParse = sdf.format(date);
            // String --> Date of year-mon-day 00:00:00
            Date dateParse = sdf.parse(dateStringParse);
            return dateParse;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
