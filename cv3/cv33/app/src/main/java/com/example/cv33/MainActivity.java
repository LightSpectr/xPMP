package com.example.cv33;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openAddActivity();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {

                    PolozkaNakupu returnValue = data.getParcelableExtra("Polozka");
                    TextView name = (TextView) findViewById(R.id.textViewName);
                    TextView price = (TextView) findViewById(R.id.textViewPrice);
                    TextView count = (TextView) findViewById(R.id.textViewCount);
                    TextView doneText = (TextView) findViewById(R.id.textViewDone);
                    name.setText(returnValue.nazev);
                    price.setText(String.valueOf(returnValue.cena));
                    count.setText(String.valueOf(returnValue.pocet));
                    if (returnValue.splneno)
                        doneText.setText("Ano");
                    else {
                        doneText.setText("Ne");
                    }
                }
                break;
            }
        }
    }
    private void  openAddActivity(){
        Intent intent = new Intent(this, AddActivity.class);

        startActivityForResult(intent, 1);
    }
}