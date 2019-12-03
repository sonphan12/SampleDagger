package com.example.sampledagger.di;

import com.example.sampledagger.ui.MainActivity;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        StorageModule.class
})
public interface AppComponent {
    void inject(MainActivity activity);
}
