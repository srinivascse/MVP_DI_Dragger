package com.example.hyundai.mvp.Login;

import com.example.hyundai.Activity.UserList;
import com.example.hyundai.pojo.Users;

import java.util.List;

public interface LoginContract {


    interface View {
        void onLoginSuccess();

        String getEmail();

        String getPassword();

        void showInputError(String message);

        void setPassword(String userId);

        void setEmail(String password);

        String getUserName();

        void setUserName(String userName);
    }

    interface Presenter {
        void onLoginClick();
    }
}
