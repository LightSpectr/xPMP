package com.example.cv33;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button buttonAddBack = (Button) findViewById(R.id.buttonAddBack);
        buttonAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.editTextName);
                EditText count = (EditText) findViewById(R.id.editTextCount);
                EditText price = (EditText) findViewById(R.id.editTextPrice);
                Switch SwitchDone = (Switch) findViewById(R.id.switchDone);
                PolozkaNakupu p = new PolozkaNakupu(name.getText().toString(),
                        Integer.parseInt(count.getText().toString()),
                        Integer.parseInt(price.getText().toString()),
                        SwitchDone.isChecked()
                        );

                Intent resultIntent = new Intent();
                resultIntent.putExtra("Polozka", p);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
    }
}