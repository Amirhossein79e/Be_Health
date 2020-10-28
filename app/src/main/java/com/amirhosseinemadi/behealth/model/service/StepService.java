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
import androidx.lifecycle.MutableLiveData;
import com.amirhosseinemadi.behealth.R;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.common.PrefManager;
import com.amirhosseinemadi.behealth.view.MainActivity;
import com.amirhosseinemadi.behealth.view.StepFragment;

import java.util.Timer;
import java.util.TimerTask;

public class StepService extends Service {

    private SensorManager sensorManager;
    private SensorEventListener stepDetectorListener;
    private Sensor stepCounter;
    private Sensor stepDetector;
    private Notification.Builder notification;
    private int step;
    private int time;
    public static MutableLiveData<Integer> timeLiveData = new MutableLiveData<>();
    public static MutableLiveData<Float> stepLiveData = new MutableLiveData<>();
    private float realStepPreferences;
    private Timer timer;
    private Timer stepChecker;
    private float realStep;
    private float previousStep = 0f;
    private PrefManager prefManager;
    public static Integer isRunning = null;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        PendingIntent pendingIntent = PendingIntent.getService(this,1,new Intent(this, MainActivity.class),PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("2329","Walking Channel",NotificationManager.IMPORTANCE_LOW);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.createNotificationChannel(channel);
            notification = new Notification.Builder(this,"2329");
        }else
        {
            notification = new Notification.Builder(this);
        }
        notification.setPriority(Notification.PRIORITY_LOW);
        notification.setContent(new RemoteViews(getPackageName(),R.layout.notification_layout));
        notification.setSmallIcon(R.drawable.ic_calories);
        notification.setContentIntent(pendingIntent);
        startForeground(2329,notification.build());

        isRunning = 0;

        prefManager = Application.dComponent.prefManager();

        class asyncStep extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {

                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

                timeLiveData.postValue(prefManager.getTime());

                SensorEventListener stepCounterListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {

                        realStep++;
                        realStepPreferences = event.values[0];
                        //System.out.println(event.values[0]);

                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {

                    }
                };

                sensorManager.registerListener(stepCounterListener, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);

                stepDetectorListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {

                        step++;
                        if (step>=20)
                        {
                            step=0;
                            sensorManager.unregisterListener(stepDetectorListener);

                            timer = new Timer();
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run()
                                {
                                    time++;
                                    //System.out.println(time);
                                }
                            },1000,1000);

                            stepChecker = new Timer();
                            stepChecker.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run()
                                {
                                    if (previousStep==realStep)
                                    {
                                        timer.cancel();
                                        sensorManager.registerListener(stepDetectorListener, stepDetector, SensorManager.SENSOR_DELAY_NORMAL);
                                        stepChecker.cancel();
                                    }
                                    previousStep = realStep;

                                    if (isFirst)
                                    {
                                        if (intent.getAction()!=null)
                                        {
                                            if (intent.getAction().equals("REBOOT"))
                                            {
                                                Looper.prepare();
                                                prefManager.setPreviousStep(0);
                                                Toast.makeText(StepService.this, "Hello", Toast.LENGTH_SHORT).show();
                                                prefManager.setStep(realStepPreferences + prefManager.getBackupStep());
                                                prefManager.setBackupStep(0);
                                            }
                                        }else
                                        {
                                            prefManager.setStep(realStepPreferences-prefManager.getPreviousStep());
                                            prefManager.setBackupStep(realStepPreferences-prefManager.getPreviousStep());
                                        }
                                        isFirst = false;
                                    }else
                                    {
                                        prefManager.setStep(realStepPreferences-prefManager.getPreviousStep());
                                        prefManager.setBackupStep(realStepPreferences-prefManager.getPreviousStep());
                                    }

                                    int previousTime = prefManager.getTime();
                                    prefManager.setTime(previousTime+time);
                                    time = 0;


                                    if (StepFragment.isRunning==1)
                                    {
                                        stepLiveData.postValue(prefManager.getStep());
                                        timeLiveData.postValue(prefManager.getTime());
                                    }

                                }
                            },5000,10000);

                        }

                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {

                    }
                };


                 sensorManager.registerListener(stepDetectorListener,stepDetector,SensorManager.SENSOR_DELAY_NORMAL);


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

}
