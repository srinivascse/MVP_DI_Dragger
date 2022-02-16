package com.example.hyundai.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hyundai.MyApplication;

import com.example.hyundai.R;
import com.example.hyundai.RecyclerViewAdapter;
import com.example.hyundai.di.component.ApplicationComponent;
import com.example.hyundai.di.component.DaggerMainActivityComponent;
import com.example.hyundai.di.component.MainActivityComponent;
import com.example.hyundai.di.module.MainActivityContextModule;
import com.example.hyundai.di.module.MainActivityMvpModule;
import com.example.hyundai.di.qualifier.ActivityContext;
import com.example.hyundai.di.qualifier.ApplicationContext;
import com.example.hyundai.mvp.MainActivityContract;
import com.example.hyundai.mvp.PresenterImpl;
import com.example.hyundai.pojo.Users;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import javax.inject.Inject;

public class UserList extends Activity implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;


    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);

         MaterialToolbar toolbar = findViewById(R.id.toolbar);




        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                                               @Override
                                               public boolean onMenuItemClick(MenuItem item) {
                                                   switch(item.getItemId()){
                                                       case R.id.add:


                                                            Intent in=new Intent(UserList.this,LoginScreen.class);
                                                            startActivity(in);
                                                           break;
                                                       // TODO: Other cases
                                                   }
                                                   return true;
                                               }
                                           }

        )

        ;

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView = findViewById(R.id.list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();


    }

    @Override
    public void launchIntent(LinkedTreeMap<Object,Object> name) {

         startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
    }

    @Override
    public void showData(List<Users.Datum> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}