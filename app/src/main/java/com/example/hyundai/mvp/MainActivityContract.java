package com.example.hyundai.mvp;


import com.example.hyundai.pojo.Users;

import java.util.List;

public interface MainActivityContract {
    interface View {
        void showData(List<Users.Datum> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void loadData();
    }
}
