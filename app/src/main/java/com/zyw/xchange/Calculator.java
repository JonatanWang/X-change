package com.zyw.xchange;

import java.util.ArrayList;

/**
 * Created by Zhengyu Wang on 2016-11-15.
 * Email: zhengyuw@kth.se
 * The Calculator which does the exchanges of currencies according to the rates saved in the local file.
 */

public class Calculator {

    //Member variables
    private String currencyFrom;
    private String currencyTo;

    // Constructor
    public Calculator(String currencyFrom, String currencyTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    // The business logic of currency exchanges.
    public double getRelativeRate(ArrayList<Currency> currencies) {
        double result = 0;
        if (currencyFrom.equals(currencyTo)) { // When two currencies are the same, e.g. SEK to SEK
            result = 1.00;
        } else if (currencyFrom.equals("EUR")) { // When the from currency is EUR, the basic currency rate list is applied.
            for (int i = 0; i < currencies.size(); i ++) {
                if (currencies.get(i).getName().equals(currencyTo)) {
                    result = (double) Double.parseDouble(currencies.get(i).getRate());
                }
            }
        } else { // When the two currencies are different, the relative rates are applied.
            for (int i = 0; i < currencies.size(); i ++) {
                for (int j = 0; j < currencies.size(); j ++) {
                    if (currencies.get(i).getName().equals(currencyFrom) && currencies.get(j).getName().equals(currencyTo)) {
                       double rateFrom = (double) Double.parseDouble(currencies.get(i).getRate());
                        double rateTo = (double) Double.parseDouble(currencies.get(j).getRate());
                        result = (double) (rateTo / rateFrom);
                    }
                }
            }
        }
        return result;
    }
}
