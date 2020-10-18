package com.amirhosseinemadi.behealth.model.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.amirhosseinemadi.behealth.R;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.view.MainActivity;

public class StepService extends Service implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensor;
    private Notification.Builder notification;
    public static Integer isRunning = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        PendingIntent pendingIntent = PendingIntent.getService(this,1,new Intent(this, MainActivity.class),PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("2329","Walking Channel",NotificationManager.IMPORTANCE_NONE);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.createNotificationChannel(channel);
            notification = new Notification.Builder(this,"2329");
        }else
        {
            notification = new Notification.Builder(this);
            notification.setPriority(Notification.PRIORITY_MIN);
        }
        notification.setContent(new RemoteViews(getPackageName(),R.layout.notification_layout));
        notification.setSmallIcon(R.drawable.ic_notification);
        startForeground(2329,notification.build());
        isRunning = 0;

        class asyncStep extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
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
    public void onSensorChanged(SensorEvent event) {


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
