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
public class LoginActivityContextModule {
    private Activity loginScreen;


    public Context context;

    public LoginActivityContextModule(Activity loginScreen) {
        this.loginScreen = loginScreen;
        context = loginScreen;
    }




    @Provides
    @ActivityScope
    public Activity providesLoginActivity() {
        return loginScreen;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
