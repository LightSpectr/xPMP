package com.example.cv1final;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.app.AlertDialog;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCalc = (Button) findViewById(R.id.buttonCalculate);
        Button buttonCAT = (Button) findViewById(R.id.buttonBMI);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calc();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        buttonCAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               openCatActivity();
            }
        });

    }
    private void calc(){
        try {
            EditText weight = (EditText) findViewById(R.id.editTextTextWeight);
            EditText height = (EditText) findViewById(R.id.editTextTextHeight);
            TextView res = (TextView) findViewById(R.id.textViewRes);
            double w = Double.parseDouble(weight.getText().toString());
            double h = Double.parseDouble(height.getText().toString()) * 0.01;
            res.setText(String.format("%.2f", (w / ( h * h) )));
        }
        catch (NumberFormatException e) {

            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("wrong format")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    
    private void  openCatActivity(){
        Intent intent = new Intent(this, BMIcat.class);
        startActivity(intent);
    }


}