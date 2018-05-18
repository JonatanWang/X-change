package com.zyw.xchange;

import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Zhengyu Wang on 2016-11-12.
 * The class parses XML format file to desired strings and saves them in an array list.
 */
public class SaxParserUtils {

    private ArrayList<Currency> currencies;

    public ArrayList<Currency> parserXmlBySax(InputStream inputStream) throws Exception
    {
        // Build a SAXParserFactory instance
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Instantiate a SAXParser class
        SAXParser parser = factory.newSAXParser();
        // Instantiate XmlHandler class
        XmlHandler handler = new XmlHandler();
        // Analize the read-in XML file by the XmlHandler
        parser.parse(inputStream, handler);
        handler.getRawTime();
        // Set value to the ArrayList of currencies
        this.setCurrencies(handler);
        return this.getCurrencies();
    }

    private void setCurrencies(XmlHandler handler) {
        this.currencies = handler.getCurrencies();
    }

    // Method to obtain the sorted data as an ArrayList
    public ArrayList<Currency> getCurrencies() {
        return this.currencies;
    }

}
