package com.amirhosseinemadi.behealth.model.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.amirhosseinemadi.behealth.common.Application;

public class RebootListener extends BroadcastReceiver {

    
    @Override
    public void onReceive(Context context, Intent intent) {

        Application.dComponent.prefManager()
                .setPreviousStep(0)
                .setReboot(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            context.startForegroundService(new Intent(context, StepService.class).setAction("REBOOT"));
        }else
        {
            context.startService(new Intent(context, StepService.class).setAction("REBOOT"));
        }

    }
}
