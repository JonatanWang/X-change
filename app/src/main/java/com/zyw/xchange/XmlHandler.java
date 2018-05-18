package com.zyw.xchange;

import android.annotation.SuppressLint;
import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static android.content.ContentValues.TAG;

/**
 * Created by Zhengyu Wang on 2016-11-11.
 * The class handles XML file with its embedded attributes like <Cube></Cube>.
 */

public class XmlHandler extends DefaultHandler {

    private String time;
    private ArrayList<Currency> currencies = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (localName.equals("Cube")) {
            // This is a Cube
            if (attributes.getIndex("", "time") != -1) {
                // This Cube has the time
                time = attributes.getValue(attributes.getIndex("", "time"));
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // The first element will only contain the time stamp
                // Set first element's "name" and "rate" to default "EUR" and "1.0" for further calculation's sake.
                Currency currency = new Currency();
                currency.setTime(date);
                currency.setName("EUR");
                currency.setRate("1.0");
                currencies.add(currency);

            } else if (attributes.getIndex("", "currency") != -1 && attributes.getIndex("", "rate") != -1) {

                String name = attributes.getValue(attributes.getIndex("", "currency"));
                String rate = attributes.getValue(attributes.getIndex("", "rate"));
                if (name != null && rate != null) {
                    Currency currency = new Currency();
                    // The elements from and with the second will contain only name and rate
                    // Leave the "time" to be null as default.
                    currency.setName(name);
                    currency.setRate(rate);
                    currencies.add(currency);
                }
            } else {
                // This Cube has neither the time or [neither the desired values: currency & rate].
            }
        }
    }

    public String getRawTime() {

        System.out.println("time--> " + time);
        return time;
    }

    public ArrayList<Currency> getCurrencies() {
        Log.i(TAG, currencies.toString());
        return currencies;
    }
}
