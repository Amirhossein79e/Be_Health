package com.amirhosseinemadi.behealth.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.amirhosseinemadi.behealth.R;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.common.PrefManager;
import com.amirhosseinemadi.behealth.databinding.ActivityMainBinding;
import com.amirhosseinemadi.behealth.databinding.DetailDialogBinding;
import com.amirhosseinemadi.behealth.model.service.StepBroadcast;
import com.amirhosseinemadi.behealth.model.service.StepService;
import com.amirhosseinemadi.behealth.viewModel.DialogVm;
import com.amirhosseinemadi.behealth.viewModel.MainVm;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    /*
    - Step count in stepFragment
    - walking time calculator
    - Stride
    - User Information
     */

    private PrefManager prefManager;
    private AlarmManager alarmManager;
    private Intent stepBroadcastIntent;
    private PendingIntent stepPending;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        MainVm viewModel = new MainVm();
        binding.setViewModel(viewModel);

        prefManager = Application.dComponent.prefManager();

        binding.bottomNavigationMain.setOnNavigationItemSelectedListener(this::onBottomNavMainItemSelected);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,new StepFragment()).commit();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor STEP_COUNTER = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (prefManager.getHeight()==0)
        {
            dialog = new Dialog(MainActivity.this);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.layout_radius));
            DetailDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(MainActivity.this),R.layout.detail_dialog,null,false);
            DialogVm dialogVm = new DialogVm(dialog);
            dialogBinding.setViewModel(dialogVm);
            dialog.setContentView(dialogBinding.getRoot());
            dialog.show();
        }


        if (STEP_COUNTER != null)
        {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            {
                if (!(checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED))
                {
                    requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
                }
            }else
            {
                if (prefManager.getFirst())
                {
                    Intent intent = new Intent(this,StepService.class);
                    startService(intent);
                    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    stepBroadcastIntent = new Intent(this,StepBroadcast.class);
                    stepPending = PendingIntent.getBroadcast(this,0,stepBroadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setInexactRepeating(AlarmManager.RTC,System.currentTimeMillis()+3600000,AlarmManager.INTERVAL_HOUR,stepPending);
                }
            }
        }else
        {
            Snackbar.make(findViewById(R.id.layout_main),"Required Sensor not supported on your device.",Snackbar.LENGTH_LONG).setAnchorView(R.id.bottom_navigation_main).show();
        }


        if (STEP_COUNTER != null)
        {
            if (StepService.isRunning == null && !prefManager.getFirst()) {
                Intent intent = new Intent(this, StepService.class);
                startService(intent);
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                stepBroadcastIntent = new Intent(this, StepBroadcast.class);
                stepPending = PendingIntent.getBroadcast(this, 0, stepBroadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis() + 3600000, AlarmManager.INTERVAL_HOUR, stepPending);
            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        prefManager.setFirst(false);
    }

    private boolean onBottomNavMainItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.step_menu_item :
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,new StepFragment()).commit();
                break;

            case R.id.heart_menu_item :
                //TODO HeartRate Fragment
                break;

            case R.id.calories_menu_item :
                //TODO Calories Fragment
                break;

        }

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(this,StepService.class);
                    startService(intent);
                    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    stepBroadcastIntent = new Intent(this,StepBroadcast.class);
                    stepPending = PendingIntent.getBroadcast(this,0,stepBroadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setInexactRepeating(AlarmManager.RTC,System.currentTimeMillis()+3600000,AlarmManager.INTERVAL_HOUR,stepPending);
                }else
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Permission denied");
                    alertDialog.setMessage("Permission denied. Be Health application need Physical Activity permission for record your walking info." +
                            "if you want allow permission go to Settings->Apps->Be-Health->Permissions->Physical activity and allow the permission.");
                    alertDialog.setIcon(R.drawable.ic_steps);
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                }
                break;
        }
    }

}