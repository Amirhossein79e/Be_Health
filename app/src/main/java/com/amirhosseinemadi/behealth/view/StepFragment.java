package com.amirhosseinemadi.behealth.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amirhosseinemadi.behealth.R;
import com.amirhosseinemadi.behealth.databinding.FragmentStepBinding;
import com.amirhosseinemadi.behealth.viewModel.StepVm;

public class StepFragment extends Fragment {

    public StepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentStepBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step,container,false);
        StepVm viewModel = new StepVm();
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

}