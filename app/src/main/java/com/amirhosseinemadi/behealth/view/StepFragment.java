package com.amirhosseinemadi.behealth.view;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        return binding.getRoot();

    }


    @Override
    public void onResume() {
        super.onResume();
            StepService.timeLiveData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (integer != 0) {
                        String str = integer / 60 + "'";
                        viewModel.setTime(str);
                    }
                }
            });


        StepService.stepLiveData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String str = String.valueOf(integer);
                viewModel.setSteps(str);
            }
        });
        }

    }


