package com.amirhosseinemadi.behealth.view;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.amirhosseinemadi.behealth.R;
import com.amirhosseinemadi.behealth.databinding.FragmentStepBinding;
import com.amirhosseinemadi.behealth.model.service.StepService;
import com.amirhosseinemadi.behealth.viewModel.StepVm;

public class StepFragment extends Fragment {

    private StepVm viewModel;
    public static int isRunning;

    public StepFragment() {
        // Required empty public constructor
        isRunning = 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentStepBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step, container, false);
        viewModel = new StepVm(getViewLifecycleOwner());
        binding.setViewModel(viewModel);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {}

        },2000);


            return binding.getRoot();
        }


    @Override
    public void onResume() {
        super.onResume();
            StepService.timeLiveData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (integer != 0) {
                        String str = String.valueOf(integer / 60 + "'");
                        viewModel.setTime(str);
                    }
                }
            });


        StepService.stepLiveData.observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                String str = String.valueOf(aFloat);
                viewModel.setSteps(str);
            }
        });
        }

    }


