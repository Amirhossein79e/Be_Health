package com.amirhosseinemadi.behealth.common;

import android.content.Context;

import dagger.Component;

@Component(modules = DaggerModule.class)
public interface DComponent {

    Context context();
    PrefManager prefManager();

}
