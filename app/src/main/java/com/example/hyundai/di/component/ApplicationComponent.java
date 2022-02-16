package com.example.hyundai.di.component;

import android.content.Context;


import com.example.hyundai.MyApplication;
import com.example.hyundai.di.module.ContextModule;
import com.example.hyundai.di.module.RetrofitModule;
import com.example.hyundai.di.qualifier.ApplicationContext;
import com.example.hyundai.di.scopes.ApplicationScope;
import com.example.hyundai.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
