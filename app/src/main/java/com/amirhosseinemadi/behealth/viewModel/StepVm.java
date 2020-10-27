package com.amirhosseinemadi.behealth.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.amirhosseinemadi.behealth.BR;
import com.amirhosseinemadi.behealth.common.Application;
import com.amirhosseinemadi.behealth.common.PrefManager;
import com.amirhosseinemadi.behealth.model.service.StepService;

public class StepVm extends BaseObservable {

    private PrefManager prefManager;

    @Bindable
    public String steps = "0";

    public String target= "/";

    @Bindable
    public String calories = "0";

    @Bindable
    public String distance = "0";

    @Bindable
    public String time = "0";


    public StepVm(LifecycleOwner lifecycleOwner)
    {
        prefManager = Application.dComponent.prefManager();
        target = String.valueOf("/"+prefManager.getTarget());
        time = String.valueOf(prefManager.getTime()/60);
        steps = String.valueOf(prefManager.getStep());
    }


    public String getSteps() {
        notifyPropertyChanged(BR.steps);
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String  getTarget() {
        return target;
    }

    public void setTarget(String  target) {
        this.target = target;
    }

    public String getCalories() {
        notifyPropertyChanged(BR.calories);
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDistance() {
        notifyPropertyChanged(BR.distance);
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        notifyPropertyChanged(BR.time);
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
