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


    public void setFirst(boolean status)
    {
        editor.putBoolean("first",status).commit();
    }


    public boolean getFirst()
    {
        return sharedPreferences.getBoolean("first",true);
    }


    public void setHeight(int height)
    {
        editor.putInt("height",height).commit();
    }


    public int getHeight()
    {
        return sharedPreferences.getInt("height",0);
    }


    public void setWeight(int weight)
    {
        editor.putInt("weight",weight).commit();
    }


    public int getWeight()
    {
        return sharedPreferences.getInt("weight",0);
    }


    public void setGender(String gender)
    {
        editor.putString("gender",gender).commit();
    }


    public String getGender()
    {
        return sharedPreferences.getString("gender","");
    }


    public void setStep(float step)
    {
        editor.putFloat("step",step).commit();
    }


    public float getStep()
    {
        return sharedPreferences.getFloat("step",0f);
    }


    public void setTime(int time)
    {
        editor.putInt("time",time).commit();
    }


    public int getTime()
    {
        return sharedPreferences.getInt("time",0);
    }



}
