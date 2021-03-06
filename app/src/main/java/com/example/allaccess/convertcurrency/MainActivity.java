package com.example.allaccess.convertcurrency;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import static com.example.allaccess.convertcurrency.R.mipmap.ic_launcher_foreground;


public class MainActivity extends AppCompatActivity {

    double userAmount = 0;
    double usd_to_JPY = 106.846;// exchange rate as of 12:41 am, April 5
    double jpy_to_USD = 1/usd_to_JPY;// set for simple division to avoid rounding errors
    double endResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// ------------ display Launcher Icon in home bar ---------
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(ic_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        final EditText amount = findViewById(R.id.amountEntered);// --- userAmount from text box
        final TextView displayResult = findViewById(R.id.convertedAmount);
        final RadioButton dollarToYen = findViewById(R.id.radioUsToNippon);// ---- find direction to convert
        final RadioButton yenToDollar = findViewById(R.id.radioNipponToUs);//
        final Button convert = findViewById(R.id.buttonConvert);

        convert.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                userAmount = Double.parseDouble(amount.getText().toString());
                NumberFormat currencyJPY = NumberFormat.getCurrencyInstance(Locale.JAPAN);//---- yen is the only unit of currency in Nippon and has no decimals
                NumberFormat currencyUSD = NumberFormat.getCurrencyInstance(Locale.US);

                if (dollarToYen.isChecked()) {
                    endResult = userAmount * usd_to_JPY;
                    if (userAmount > 10000) {
                        Toast.makeText(MainActivity.this, "This app can only calculate up to 10,000 USD", Toast.LENGTH_LONG).show();
                    } else {
                        String toNippon = (currencyUSD.format(userAmount) + " USD equals " + currencyJPY.format(endResult) + " JPY");
                        displayResult.setText(toNippon);
                                            }
                }// ----- END dollarToYen IF
                if (yenToDollar.isChecked()) {
                    endResult = userAmount * jpy_to_USD;
                    if (endResult > 10000) {
                        Toast.makeText(MainActivity.this, "This app can only calculate up to 10,000 USD or approximately 1,000,000 JPY", Toast.LENGTH_LONG).show();
                    } else {
                        String toUSA = (currencyJPY.format(userAmount) + " JPY equals " + currencyUSD.format(endResult) + " USD");
                        displayResult.setText(toUSA);
                    }
                }// ----- END yenToDollar IF
            }
        });

    }// --------- END onCreate
}//-------END MainActivity
