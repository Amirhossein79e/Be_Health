package com.amirhosseinemadi.behealth.common;

import android.content.Context;

import com.amirhosseinemadi.behealth.model.others.Calculator;

import dagger.Component;

@Component(modules = DaggerModule.class)
public interface DComponent {

    Context context();
    PrefManager prefManager();
    Calculator calculator();

}
