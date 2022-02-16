package com.example.hyundai.di.module;

import com.example.hyundai.di.scopes.ActivityScope;
import com.example.hyundai.mvp.Login.LoginContract;
import com.example.hyundai.mvp.MainActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityMvpModule {
    private final LoginContract.View mView;


    public LoginActivityMvpModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    LoginContract.View provideView() {
        return mView;
    }


}
