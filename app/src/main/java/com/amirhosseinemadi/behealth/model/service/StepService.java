package com.amirhosseinemadi.behealth.model.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class StepService extends Service implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        class asyncStep extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
                Looper.prepare();
                Toast.makeText(StepService.this, "Hello", Toast.LENGTH_SHORT).show();
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(StepService.this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
                return null;
            }
        }

        new asyncStep().execute();

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        /*Intent intent = new Intent(this,this.getClass());
        intent.setPackage(getPackageName());
        PendingIntent pendingIntent = PendingIntent.getService(this,2,intent,PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime()+1000,pendingIntent);*/

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        Intent intent = new Intent(this,this.getClass());
        intent.setPackage(getPackageName());
        PendingIntent pendingIntent = PendingIntent.getService(this,2,intent,PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime()+1000,pendingIntent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        System.out.println(event.values[0]);
        Toast.makeText(this, "Step", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}
