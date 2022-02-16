package com.example.hyundai.di.component;

import android.content.Context;

import com.example.hyundai.Activity.LoginScreen;
import com.example.hyundai.Activity.UserList;
import com.example.hyundai.di.module.AdapterModule;
import com.example.hyundai.di.module.LoginActivityMvpModule;
import com.example.hyundai.di.module.MainActivityMvpModule;
import com.example.hyundai.di.qualifier.ActivityContext;
import com.example.hyundai.di.scopes.ActivityScope;
import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class},
        dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(UserList userList);



}
