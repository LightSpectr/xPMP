package com.example.vypocetbmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public EditText e1, e2;
    TextView t1;
    double vaha, vyska;
    public boolean getNumbers() {

        //checkAndClear();
        // defining the edit text 1 to e1
        e1 = (EditText) findViewById(R.id.vaha);

        // defining the edit text 2 to e2
        e2 = (EditText) findViewById(R.id.vyska);

        // defining the text view to t1
        t1 = (TextView) findViewById(R.id.result);

        // taking input from text box 1
        String s1 = e1.getText().toString();

        // taking input from text box 2
        String s2 = e2.getText().toString();



        if(s1.equals("Vyplnte vahu") && s2.equals(null))
        {
            String result = "Vyplnte vysku";
            e2.setText(result);
            return false;
        }
        if(s1.equals(null) && s2.equals("Vyplnte vysku"))
        {
            String result = "Vyplnte vahu";
            e1.setText(result);
            return false;
        }
        if(s1.equals("Vyplnte vahu") || s2.equals("Vyplnte vysku"))
        {
            return false;
        }

        if((!s1.equals(null) && s2.equals(null))|| (!s1.equals("") && s2.equals("")) ){

            String result = "Vyplnte vysku";

            e2.setText(result);
            return false;
        }
        if((s1.equals(null) && !s2.equals(null))|| (s1.equals("") && !s2.equals("")) ){
            //checkAndClear();
            String result = "Vyplnte vahu";
            e1.setText(result);
            return false;
        }
        if((s1.equals(null) && s2.equals(null))|| (s1.equals("") && s2.equals("")) ){
            //checkAndClear();
            String result1 = "Vyplnte vahu";
            e1.setText(result1);
            String result2 = "Vyplnte vysku";
            e2.setText(result2);
            return false;
        }

        else {
            // converting string to int.
            vaha = Integer.parseInt(s1);

            // converting string to int.
            vyska = Integer.parseInt(s2);


        }

        return true;
    }
    public void clearTextNum1(View v) {

        // get the input numbers
        e1.getText().clear();
    }
    public void clearTextNum2(View v) {

        // get the input numbers
        e2.getText().clear();
    }
    @SuppressLint("SetTextI18n")
    public void doDiv(View v) {
        //checkAndClear();
        // get the input numbers
        if (getNumbers()) {

            // displaying the text in text view assigned as t1
            double sum = vaha/((vyska/100)*(vyska/100));
            t1.setText(Double.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}