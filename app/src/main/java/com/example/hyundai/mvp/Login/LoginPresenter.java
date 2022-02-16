package com.example.hyundai.mvp.Login;


import android.os.Handler;
import android.widget.Toast;


import com.example.hyundai.Activity.UserList;
import com.example.hyundai.mvp.MainActivityContract;
import com.example.hyundai.pojo.Users;
import com.example.hyundai.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginPresenter implements LoginContract.Presenter {

    APIInterface apiInterface;
    LoginContract.View mView;

    @Inject
    public LoginPresenter(APIInterface apiInterface, LoginContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void onLoginClick() {

        if(mView.getUserName()==null || mView.getUserName().trim().length()==0)
                mView.showInputError("Please Enter the Name");
        else if(mView.getEmail()==null || mView.getEmail().trim().length()==0)
            mView.showInputError("Please Enter the Mail id");
        else if(mView.getPassword()==null || mView.getPassword().trim().length()==0)
            mView.showInputError("Please Enter the Password");
        else {
            Users mProfilevalue = new  Users();
            Users.Datum mProfile=mProfilevalue.new Datum();
            mProfile.setFirst_name(mView.getUserName());
            mProfile.setEmail(mView.getEmail());
            mProfile.setLast_name("Test");
            mProfile.setAvatar("https://reqres.in/img/faces/4-image.jpg");
            apiInterface.doLoginApiCall(mProfile).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Users>() {
                        @Override
                        public void onCompleted() {
                            System.out.println("process completed");
                        }

                        @Override
                        public void onError(Throwable e) {

                            System.out.println("Error Occur");

                        }

                        @Override
                        public void onNext(Users data) {

                            System.out.println(data);

                            mView.onLoginSuccess();
                        }
                    });

        }
    }
}
