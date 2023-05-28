package com.example.porovnanicen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    private void showAlertDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result")
                .setMessage(result)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
