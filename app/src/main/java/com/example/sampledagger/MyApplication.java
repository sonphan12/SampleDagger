package com.example.sampledagger;

import android.app.Application;

import com.example.sampledagger.di.AppComponent;
import com.example.sampledagger.di.ApplicationModule;
import com.example.sampledagger.di.DaggerAppComponent;
import com.example.sampledagger.di.NetworkModule;
import com.example.sampledagger.di.StorageModule;

public class MyApplication extends Application {
    private AppComponent mAppComponent;

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .networkModule(new NetworkModule())
                .storageModule(new StorageModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static MyApplication getInstance() {
        return INSTANCE;
    }
}
