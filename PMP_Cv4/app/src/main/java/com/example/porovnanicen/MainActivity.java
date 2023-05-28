package com.example.porovnanicen;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText mPrice1EditText;
    private EditText mGrammage1EditText;
    private EditText mPrice2EditText;
    private EditText mGrammage2EditText;
    private Button mCompareButton;

    private TextView mResultTextView;

    private double mPrice1;
    private double mPrice2;
    private double mGrammage1;
    private double mGrammage2;

    private static final String DATA_FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrice1EditText = findViewById(R.id.price1_edit_text);
        mGrammage1EditText = findViewById(R.id.grammage1_edit_text);
        mPrice2EditText = findViewById(R.id.price2_edit_text);
        mGrammage2EditText = findViewById(R.id.grammage2_edit_text);

        mCompareButton = findViewById(R.id.compare_button);

        mResultTextView = findViewById(R.id.result_text_view);

        // Read data from file if it exists
        readDataFromFile();

        // Set the values in EditText fields
        mPrice1EditText.setText(String.valueOf(mPrice1));
        mGrammage1EditText.setText(String.valueOf(mGrammage1));
        mPrice2EditText.setText(String.valueOf(mPrice2));
        mGrammage2EditText.setText(String.valueOf(mGrammage2));

        mCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price1 = mPrice1EditText.getText().toString();
                String price2 = mPrice2EditText.getText().toString();
                String grammage1 = mGrammage1EditText.getText().toString();
                String grammage2 = mGrammage2EditText.getText().toString();

                if (TextUtils.isEmpty(price1) || TextUtils.isEmpty(price2) || TextUtils.isEmpty(grammage1) || TextUtils.isEmpty(grammage2)) {
                    mResultTextView.setText("Please enter all values");
                    return;
                }

                mPrice1 = Double.parseDouble(price1);
                mPrice2 = Double.parseDouble(price2);
                mGrammage1 = Double.parseDouble(grammage1);
                mGrammage2 = Double.parseDouble(grammage2);

                // Write data to file
                writeDataToFile();

                double pricePerKg1 = mPrice1 / (mGrammage1 / 1000);
                double pricePerKg2 = mPrice2 / (mGrammage2 / 1000);

                String result;

                if (pricePerKg1 < pricePerKg2) {
                    result = "Good 1 is cheaper";
                } else if (pricePerKg2 < pricePerKg1) {
                    result = "Good 2 is cheaper";
                } else {
                    result = "Both goods have the same price per kg";
                }

                showAlertDialog(result);
            }
        });
    }

    private void writeDataToFile() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            // Write the values to the file
            writer.write(String.valueOf(mPrice1));
            writer.newLine();
            writer.write(String.valueOf(mPrice2));
            writer.newLine();
            writer.write(String.valueOf(mGrammage1));
            writer.newLine();
            writer.write(String.valueOf(mGrammage2));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readDataFromFile() {
        try {
            FileInputStream fileInputStream = openFileInput(DATA_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            // Read the values from the file
            String line;
            if ((line = reader.readLine()) != null) {
                mPrice1 = Double.parseDouble(line);
            }
            if ((line = reader.readLine()) != null) {
                mPrice2 = Double.parseDouble(line);
            }
            if ((line = reader.readLine()) != null) {
                mGrammage1 = Double.parseDouble(line);
            }
            if ((line = reader.readLine()) != null) {
                mGrammage2 = Double.parseDouble(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlertDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result")
                .setMessage(result)
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
