package com.example.hyundai;

import android.app.Activity;
import android.app.Application;

import com.example.hyundai.di.component.ApplicationComponent;
import com.example.hyundai.di.component.DaggerApplicationComponent;
import com.example.hyundai.di.module.ContextModule;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static com.example.hyundai.MyApplication get(Activity activity){
        return (com.example.hyundai.MyApplication) activity.getApplication();
    }



    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

