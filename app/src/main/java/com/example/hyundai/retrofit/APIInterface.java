package com.example.hyundai.retrofit;

import com.example.hyundai.Activity.UserList;
import com.example.hyundai.pojo.Users;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface {

    @GET("users/?")
    Observable<Users> getData(@Query("limit") String limit);


    @POST("users")
    Observable<Users> doLoginApiCall(@Body Users.Datum mRequest);
}
