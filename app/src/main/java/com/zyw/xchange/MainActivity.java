package com.zyw.xchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.valueOf;

/**
 * Created by Zhengyu Wang on 2016-11-12.
 * The Main Activity
 */
public class MainActivity extends AppCompatActivity {

    // Initiate the GUI elements
    private EditText inputAmount;
    private Spinner spinnerFrom, spinnerTo;
    //private Button buttonXchange;
    private  Button buttonReset;
    private TextView textViewRate;
    private TextView textViewInfo1;
    private final String source = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputAmount = (EditText) findViewById(R.id.editCurrency);
        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        //buttonXchange = (Button) findViewById(R.id.buttonConvert);
        textViewRate = (TextView) findViewById(R.id.textRate);
        textViewInfo1 = (TextView) findViewById(R.id.textViewInfo1);
        buttonReset = (Button) findViewById(R.id.button_network);

        final ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                String info, info1 = null;
                String exchangeRate, exchangeRateReverse = null;
                String inputAmountInString = inputAmount.getText().toString();
                final ArrayList<Currency> currenciesNew = DataInfoCache.loadListCache(getApplicationContext(), "myCache");
                String currencyFrom = valueOf(spinnerFrom.getSelectedItem());
                String currencyTo = valueOf(spinnerTo.getSelectedItem());
                final Model model = new Model(currenciesNew, currencyFrom, currencyTo);


                if (inputAmountInString.equals("")) {
                    //textViewRate.setText("Nothing");
                    info = String.valueOf(spinnerFrom.getSelectedItem()) + "\n => \n" + String.valueOf(spinnerTo.getSelectedItem());

                } else {
                    // Get the lasted data from the local file for the purpose of calculation.
                    exchangeRate = model.getExchangeRate(inputAmountInString);
                    exchangeRateReverse = model.getExchangeRateReverse(inputAmountInString);
                    info = Double.valueOf(inputAmountInString) + "\n => \n" + exchangeRate;
                    info1 = "\nReverse: " + spinnerTo.getSelectedItem() + " " +
                            Double.valueOf(inputAmountInString) + " => " + spinnerFrom.getSelectedItem() + " " + exchangeRateReverse;
                }
                textViewRate.setText(info);
                textViewInfo1.setText(info1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        spinnerTo.setAdapter(adapter);

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String info, info1 = null;
                String exchangeRate, exchangeRateReverse = null;
                String inputAmountInString = inputAmount.getText().toString();
                final ArrayList<Currency> currenciesNew = DataInfoCache.loadListCache(getApplicationContext(), "myCache");
                String currencyFrom = valueOf(spinnerFrom.getSelectedItem());
                String currencyTo = valueOf(spinnerTo.getSelectedItem());
                final Model model = new Model(currenciesNew, currencyFrom, currencyTo);


                if (inputAmount.getText().toString().equals("")) {
                    //textViewRate.setText("Nothing");
                    info = String.valueOf(spinnerFrom.getSelectedItem()) + "\n => \n" + String.valueOf(spinnerTo.getSelectedItem());

                } else {
                    // Get the lasted data from the local file for the purpose of calculation.
                    exchangeRate = model.getExchangeRate(inputAmountInString);
                    exchangeRateReverse = model.getExchangeRateReverse(inputAmountInString);
                    info = Double.valueOf(inputAmountInString) + "\n => \n" + exchangeRate;
                    info1 = "\nReverse: " + spinnerTo.getSelectedItem() + " " +
                            Double.valueOf(inputAmountInString) + " => " + spinnerFrom.getSelectedItem() + " " + exchangeRateReverse;
                }
                textViewRate.setText(info);
                textViewInfo1.setText(info1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        // Set listener to the edit view input area
        inputAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Load the data from saved local file.
                ArrayList<Currency> currencies = DataInfoCache.loadListCache(getApplicationContext(), "myCache");
                textViewUpdater updater = new textViewUpdater(currencies);
                updater.updateTextView();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int length = charSequence.length();
                if (length != 0) {
                    // Read in only the last character behind the cursor.
                    char character = charSequence.charAt(length - 1);
                    if ((character != 46) && (character < 48 || character > 57)) { // Limit the input to 0 - 9 and decimal point.
                        int msgID = R.string.input_error_msg;
                        Toast.makeText(getApplicationContext(), msgID, Toast.LENGTH_LONG).show();
                    }
                    String info, info1 = null;
                    String exchangeRate, exchangeRateReverse = null;
                    String inputAmountInString = inputAmount.getText().toString();
                    final ArrayList<Currency> currenciesNew = DataInfoCache.loadListCache(getApplicationContext(), "myCache");
                    String currencyFrom = valueOf(spinnerFrom.getSelectedItem());
                    String currencyTo = valueOf(spinnerTo.getSelectedItem());
                    final Model model = new Model(currenciesNew, currencyFrom, currencyTo);

                    exchangeRate = model.getExchangeRate(inputAmountInString);
                    exchangeRateReverse = model.getExchangeRateReverse(inputAmountInString);
                    // Set the text view output before user click "Xchange!" button.
                    info = Double.valueOf(inputAmountInString) + "\n => \n" + exchangeRate;
                    info1 = "\nReverse: " + spinnerTo.getSelectedItem() + " " +
                            Double.valueOf(inputAmountInString) + " => " + spinnerFrom.getSelectedItem() + " " + exchangeRateReverse;
                    textViewRate.setText(info);
                    textViewInfo1.setText(info1);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Set listener to the "Reset" button -> clear all the text, reset currencies to "EUR".
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputAmount.setText("");
                textViewInfo1.setText("");
                spinnerFrom.setSelection(0);
                spinnerTo.setSelection(0);
                textViewInfo1.setText("");
            }
        });
    }

    @Override
    protected void onStart(){

        super.onStart();
        ArrayList<Currency> currencies = DataInfoCache.loadListCache(getApplicationContext(), "myCache");
        Log.d(TAG, currencies.toString());
        textViewUpdater updater = new textViewUpdater(currencies);
        updater.updateTextView();

        NetworkValidator nValidator = new NetworkValidator(getApplicationContext());
        // If there is network connection, check the current date & date saved in the local file.
        if (nValidator.isNetworkConnected()) {
            DateValidator dValidator = new DateValidator();
            boolean isAfter;
            if (currencies.isEmpty()) {
                isAfter = true;
            } else {
                isAfter = dValidator.getCurrentDate().after(currencies.get(0).getTime());
            }

            if(isAfter) { // If the current date is later than 24 hours of the saved date, update the file when network is available.
                try {
                    URL serverUrl = new URL(source);
                    XmlDownloader downloader = new XmlDownloader(getApplicationContext(), serverUrl);
                    downloader.start();
                    Toast.makeText(MainActivity.this, "Online: data updated.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Reloaded data :::::!!!!");

                    // Inform user that the network is available, local file updated, online mode works.
                    ArrayList<Currency> currenciesNew = DataInfoCache.loadListCache(getApplicationContext(), "myCache");
                    textViewUpdater updaterNew = new textViewUpdater(currenciesNew);
                    updaterNew.updateTextView();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // Inform user that network is not available, the saved data may be out-of-date and off-line mode works.
            Toast.makeText(MainActivity.this, "Offline, data may be out of date.", Toast.LENGTH_LONG).show();
        }
    }

    // Private class to update the information in text view area.
    private class textViewUpdater {
        private ArrayList<Currency> list = null;
        public textViewUpdater( ArrayList<Currency> list) {
            // Load the data from saved local file.
            this.list = list;
        }
        public void updateTextView() {
            // Check if there is no data saved previously.
            if (!list.isEmpty()) {
                // If there is a pre-saved local file, show the updated date.
                Date dateToShow = list.get(0).getTime();
                DateFormatConverter converter = new DateFormatConverter(dateToShow);
                textViewRate.setText("File updated: " + converter.convertDate().toString());
            } else {
                // If there is no saved data, just show from & to currency names.
                textViewRate.setText(String.valueOf(spinnerFrom.getSelectedItem()) + "\n => \n" + String.valueOf(spinnerTo.getSelectedItem()));
            }
        }
    }
}
