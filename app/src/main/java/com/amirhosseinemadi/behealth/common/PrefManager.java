package com.amirhosseinemadi.behealth.common;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Be Health",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


    public void setStep(float step)
    {
        editor.putFloat("step",step).commit();
    }


    public float getStep()
    {
        return sharedPreferences.getFloat("step",0f);
    }


    public void setFirst(boolean status)
    {
        editor.putBoolean("first",status).commit();
    }


    public boolean getFirst()
    {
        return sharedPreferences.getBoolean("first",true);
    }

}
