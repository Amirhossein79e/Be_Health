package com.amirhosseinemadi.behealth.model.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.common.PrefManager;

public class StepBroadcast extends BroadcastReceiver implements SensorEventListener {

    private PrefManager prefManager;
    private SensorManager sensorManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        prefManager = Application.dComponent.prefManager();

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        prefManager.setPreviousStep(event.values[0]);
        prefManager.setStep(0f);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
