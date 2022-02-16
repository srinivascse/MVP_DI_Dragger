package com.example.hyundai.di.module;

import android.app.Activity;

import com.example.hyundai.Activity.UserList;
import com.example.hyundai.RecyclerViewAdapter;
import com.example.hyundai.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(Activity mainActivity) {
        return (RecyclerViewAdapter.ClickListener) mainActivity;
    }
}
