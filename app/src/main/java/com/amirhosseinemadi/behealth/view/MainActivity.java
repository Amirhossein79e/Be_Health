package com.amirhosseinemadi.behealth.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.widget.Toast;
import com.amirhosseinemadi.behealth.R;
import com.amirhosseinemadi.behealth.databinding.ActivityMainBinding;
import com.amirhosseinemadi.behealth.model.service.StepService;
import com.amirhosseinemadi.behealth.viewModel.MainVm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        MainVm viewModel = new MainVm();
        binding.setViewModel(viewModel);

        binding.bottomNavigationMain.setOnNavigationItemSelectedListener(this::onBottomNavMainItemSelected);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,new StepFragment()).commit();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        {
            if (!(checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED))
            {
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION},1);
            }
        }


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
                }
                break;
        }
    }
}