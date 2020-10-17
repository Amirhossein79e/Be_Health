package com.amirhosseinemadi.behealth.common;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerModule {

    private final Context context;

    public DaggerModule(Context context)
    {
        this.context = context;
    }

    @Provides
    public Context context()
    {
        return context;
    }

    @Provides
    public PrefManager prefManager()
    {
        return new PrefManager(context);
    }

}
