package com.example.hyundai.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.hyundai.MyApplication;
import com.example.hyundai.R;
import com.example.hyundai.di.component.ApplicationComponent;


import com.example.hyundai.di.component.DaggerLoginActivityComponent;
import com.example.hyundai.di.component.LoginActivityComponent;
import com.example.hyundai.di.module.LoginActivityContextModule;
import com.example.hyundai.di.module.LoginActivityMvpModule;

import com.example.hyundai.di.module.MainActivityContextModule;
import com.example.hyundai.di.qualifier.ActivityContext;
import com.example.hyundai.di.qualifier.ApplicationContext;
import com.example.hyundai.mvp.Login.LoginContract;
import com.example.hyundai.mvp.Login.LoginPresenter;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

public class LoginScreen extends Activity implements LoginContract.View{

    TextInputEditText fullName,EmailId,password;
    LoginActivityComponent mainActivityComponent;


    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginscreen);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerLoginActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .loginActivityMvpModule(new LoginActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectLoginActivity(this);

        MaterialButton ticketButton = (MaterialButton)findViewById(R.id.login_btn);


        fullName=(TextInputEditText)findViewById(R.id.edt_fname);
        EmailId=(TextInputEditText)findViewById(R.id.edt_email);
        password=(TextInputEditText)findViewById(R.id.edt_password);


            ticketButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        presenter.onLoginClick();
                }
            });





    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(activityContext, UserList.class));
    }



    @Override
    public String getEmail() {
        return EmailId.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showInputError(String message) {
        Toast.makeText(activityContext,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPassword(String password) {
        EmailId.setText(password);
    }

    @Override
    public void setEmail(String email) {
        password.setText(email);
    }

    @Override
    public String getUserName() {
        return fullName.getText().toString();
    }

    @Override
    public void setUserName(String userName) {
        fullName.setText(userName);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
