package com.zyw.xchange;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by zyw on 2017-11-21.
 */

public class Model {

    private ArrayList<Currency> currencies;
    private Calculator calculator;
    private Calculator calculatorReverse;

    public Model(ArrayList<Currency> currencies, String currencyFrom, String currencyTo) {
        calculator = new Calculator(currencyFrom, currencyTo);
        calculatorReverse = new Calculator(currencyTo, currencyFrom);
        this.currencies = currencies;
    }

    public String getExchangeRate(String inputAmount) {
        double relativeRate = calculator.getRelativeRate(currencies);
        double inputCurrency = (double)Double.valueOf(inputAmount);
        double result = inputCurrency * relativeRate;
        BigDecimal bd = new BigDecimal(result);
        // Set result to only 2 decimals.
        BigDecimal  bd2 = bd.setScale(2,BigDecimal  .ROUND_HALF_UP);
        return  bd2.toString();
    }

    public String getExchangeRateReverse(String inputAmount) {
        double relativeRate1 = calculatorReverse.getRelativeRate(currencies);
        double inputCurrency = (double)Double.valueOf(inputAmount);
        double result1 = inputCurrency * relativeRate1;
        BigDecimal bd1= new BigDecimal(result1);
        // Set result to only 2 decimals.
        BigDecimal  bd2_1 = bd1.setScale(2,BigDecimal  .ROUND_HALF_UP);
        return  bd2_1.toString();
    }
}
