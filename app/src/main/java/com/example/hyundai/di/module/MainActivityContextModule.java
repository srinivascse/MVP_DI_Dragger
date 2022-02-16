package com.example.hyundai.di.module;

import android.app.Activity;
import android.content.Context;


import com.example.hyundai.Activity.LoginScreen;
import com.example.hyundai.Activity.UserList;
import com.example.hyundai.di.qualifier.ActivityContext;
import com.example.hyundai.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private Activity userList;


    public Context context;

    public MainActivityContextModule(Activity userList) {
        this.userList = userList;
        context = userList;
    }




    @Provides
    @ActivityScope
    public Activity providesMainActivity() {
        return userList;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
