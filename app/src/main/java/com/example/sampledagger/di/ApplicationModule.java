package com.example.sampledagger.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context mApplicationContext;

    public ApplicationModule(Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    @Provides
    public Context provideContext() {
        return mApplicationContext;
    }
}
