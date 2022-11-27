package com.example.sayacuygulamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    int upperLimit, lowerLimit;
    EditText upvalue, downvalue;
    Button uplus , uminus, lplus, lminus;
    CheckBox upVib, lowVib, upSound, lowSound;
    SetupClass setupClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        upvalue = (EditText) findViewById(R.id.txt_upperLimit);
        downvalue = (EditText) findViewById(R.id.txt_lowerLimit);
        uplus = (Button) findViewById(R.id.uPlus);
        uminus = (Button) findViewById(R.id.uMinus);
        lplus = (Button) findViewById(R.id.lPlus);
        lminus = (Button) findViewById(R.id.lMinus);

        upVib = (CheckBox) findViewById(R.id.uVib);
        lowVib = (CheckBox) findViewById(R.id.lVib);
        upSound = (CheckBox) findViewById(R.id.uSound);
        lowSound = (CheckBox) findViewById(R.id.lSound);

        Context context = getApplicationContext();
        setupClass = SetupClass.getInstance(context);


        upvalue.setText(String.valueOf(upperLimit));
        downvalue.setText(String.valueOf(lowerLimit));

        uplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upperLimit++;
                upvalue.setText(String.valueOf(upperLimit));
            }
        });

        uminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upperLimit--;
                upvalue.setText(String.valueOf(upperLimit));
            }
        });

        lplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowerLimit++;
                downvalue.setText(String.valueOf(lowerLimit));
            }
        });

        lminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowerLimit--;
                downvalue.setText(String.valueOf(lowerLimit));
            }
        });
    }

    private void getPreferences(){
        upperLimit = setupClass.upperLimit;
        lowerLimit = setupClass.lowerLimit;
        upVib.setChecked(setupClass.upperVib);
        lowVib.setChecked(setupClass.lowerVib);
        upSound.setChecked(setupClass.upperSound);
        lowSound.setChecked(setupClass.lowerSound);

    }

    @Override
    public void onPause() {
        super.onPause();
        setupClass.setValues(upperLimit, lowerLimit, upVib.isChecked(), lowVib.isChecked(), upSound.isChecked(), lowSound.isChecked());
        setupClass.saveValues();
    }

    @Override
    public void onResume(){
        super.onResume();
        setupClass.loadValues();
        getPreferences();
        upvalue.setText(String.valueOf(upperLimit));
        downvalue.setText(String.valueOf(lowerLimit));

    }

}