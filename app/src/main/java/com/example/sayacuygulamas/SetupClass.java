package com.example.sayacuygulamas;

import android.content.Context;
import android.content.SharedPreferences;

public class SetupClass {
    int upperLimit;
    int lowerLimit;
    int currentValue;

    boolean upperVib;
    boolean lowerVib;
    boolean upperSound;
    boolean lowerSound;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static SetupClass setupClass = null;

    private SetupClass(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("setup", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SetupClass getInstance(Context context){
        if(setupClass == null){
            setupClass = new SetupClass(context);
        }
        return setupClass;
    }

    public void setValues(int upperLimit, int lowerLimit, boolean upperVib,
                          boolean lowerVib, boolean upperSound,boolean lowerSound){
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.upperVib = upperVib;
        this.lowerVib = lowerVib;
        this.upperSound = upperSound;
        this.lowerSound = lowerSound;
    }

    public void setValues(int currentValue)
    {
        this.currentValue=currentValue;
        saveValues();

    }

    public void saveValues(){
        editor.putInt("upperLimit", upperLimit);
        editor.putInt("lowerLimit", lowerLimit);
        editor.putInt("currentValue", currentValue);
        editor.putBoolean("upperVib", upperVib);
        editor.putBoolean("lowerVib", lowerVib);
        editor.putBoolean("upperSound", upperSound);
        editor.putBoolean("lowerSound", lowerSound);
        editor.commit();
    }

    public void loadValues(){
        upperLimit = sharedPreferences.getInt("upperLimit", 20);
        lowerLimit = sharedPreferences.getInt("lowerLimit", -5);
        currentValue = sharedPreferences.getInt("currentValue", 0);
        upperVib = sharedPreferences.getBoolean("upperVib", true);
        lowerVib = sharedPreferences.getBoolean("lowerVib", true);
        upperSound = sharedPreferences.getBoolean("upperSound", true);
        lowerSound = sharedPreferences.getBoolean("lowerSound", true);
    }

}
