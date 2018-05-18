package com.zyw.xchange;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Zhengyu Wang on 2016-11-15.
 * Email: zhengyuw@kth.se
 * The class works to download XML file and save desired data to the local file.
 */

public class XmlDownloader extends Thread {

    private URL url;
    private Context context;

    public XmlDownloader(Context context, URL url) {
        this.context = context;
        this.url = url;
    }

    @Override
    public void run()
    {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        try {
            inputStream = HttpUtils.httpMethod(url);
            SaxParserUtils saxParserUtils = new SaxParserUtils();
            saxParserUtils.parserXmlBySax(inputStream);
            DataInfoCache.saveListCache(context, saxParserUtils.getCurrencies(), "myCache");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Xchange -> Convert is done!");
    }
}
