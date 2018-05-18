package com.zyw.xchange;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zhengyu Wang on 2016-11-12.
 * The class works to get internet connection & input stream by given URL.
 */

public class HttpUtils {

    public HttpUtils() throws IOException {
    }

    public static InputStream httpMethod(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            return inputStream;
        }
        finally {
            //urlConnection.disconnect();
        }
    }
}
