package com.example.sayacuygulamas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    Button minus, plus, btnayar;
    int upperLimit;
    int lowerLimit;
    int currentValue;


    boolean upperVib;
    boolean lowerVib;
    boolean upperSound;
    boolean lowerSound;

    SetupClass setupClass;

    Vibrator vibrator = null;
    MediaPlayer media = null;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent splashScreen = new Intent(this, SplashScreen.class);
        startActivity(splashScreen);

        value = (TextView) findViewById(R.id.value);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        btnayar = (Button) findViewById(R.id.ayar);

        Context context = getApplicationContext();
        setupClass  = SetupClass.getInstance(context);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        media = MediaPlayer.create(this, R.raw.iris);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentValue < upperLimit)
                    currentValue++;
                else{
                    if (upperVib)
                        vibrator.vibrate(1000);
                    if (upperSound)
                        media.start();
                }


                value.setText(String.valueOf(currentValue));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentValue > lowerLimit)
                    currentValue--;
                else{
                    if (upperVib)
                        vibrator.vibrate(1000);
                    if (upperSound)
                        media.start();
                }


                value.setText(String.valueOf(currentValue));
            }
        });


        btnayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(intent);
            }
        });





    }

    private void getPreferences() {
        upperLimit = setupClass.upperLimit;
        lowerLimit = setupClass.lowerLimit;
        upperVib = setupClass.upperVib;
        lowerVib = setupClass.lowerVib;
        upperSound = setupClass.upperSound;
        lowerSound = setupClass.lowerSound;
        currentValue = setupClass.currentValue;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupClass.loadValues();
        getPreferences();

    }

    @Override
    protected void onPause() {
        super.onPause();
        setupClass.setValues(currentValue);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (currentValue < upperLimit)
                currentValue+=5;
            else{
                if (upperVib)
                    vibrator.vibrate(1000);
                if (upperSound)
                    media.start();
            }
            value.setText(String.valueOf(currentValue));

        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (currentValue > lowerLimit)
                currentValue-=5;
            else{
                if (upperVib)
                    vibrator.vibrate(1000);
                if (upperSound)
                    media.start();
            }
            value.setText(String.valueOf(currentValue));

        }
        return true;
    }
}