package com.katerina.calculator;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button myButton;
    private TextView resultCount;
    private TextView resultPrice;
    private EditText length;
    private EditText width;
    private EditText price;
    private EditText area;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private double count;
    private double countPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = findViewById(R.id.my_button);
        resultCount = findViewById(R.id.text_count);
        resultPrice = findViewById(R.id.text_price);
        length = findViewById(R.id.edit_length);
        width = findViewById(R.id.edit_width);
        price = findViewById(R.id.edit_price);
        area = findViewById(R.id.edit_area);
        layout1 = findViewById(R.id.layout_1);
        layout2 = findViewById(R.id.layout_2);
        clickButton();
    }

    public void clickButton() {
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {String strLength = length.getText().toString();
                    double dbLength = Double.parseDouble(strLength);
                    String strWidth = width.getText().toString();
                    double dbWidth = Double.parseDouble(strWidth);
                    String strPrise = price.getText().toString();
                    double dbPrice = Double.parseDouble(strPrise);
                    String strArea = area.getText().toString();
                    double dbArea = Double.parseDouble(strArea);

                    count = dbArea / (dbLength * dbWidth);
                    countPrice = dbPrice * dbArea;

                    DecimalFormat df =new DecimalFormat("#.####");

                    String strCount = String.valueOf(df.format(count));
                    String strCountPrice = String.valueOf(df.format(countPrice));

                    resultCount.setText(strCount);
                    resultPrice.setText(strCountPrice);

                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);

                    hideKeyboard(MainActivity.this);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.error), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager)
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
