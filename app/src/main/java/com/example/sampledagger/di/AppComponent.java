package com.example.sampledagger.di;

import com.example.sampledagger.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        StorageModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
}
