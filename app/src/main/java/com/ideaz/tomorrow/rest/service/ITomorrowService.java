package com.ideaz.tomorrow.rest.service;

import com.ideaz.tomorrow.rest.model.TomorrowActivity;
import com.ideaz.tomorrow.rest.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ITomorrowService {
    @GET("/api/v1/user/{id}")
    public void getUser(@Path("id") Integer userId, Callback<User> cb);

    @GET("/api/v1/user")
    public void getUsers(Callback<List<User>> cb);

    @POST("/api/v1/user")
    public void createUser(@Body User user, Callback<User> cb);

    @GET("/api/v1/activity")
    public void getActivities(Callback<List<TomorrowActivity>> cb);

    @POST("/api/v1/activity")
    public void createActivity(@Body TomorrowActivity tomorrowActivity , Callback<TomorrowActivity> cb);

}
