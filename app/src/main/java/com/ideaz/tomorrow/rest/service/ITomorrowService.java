package com.ideaz.tomorrow.rest.service;

import com.ideaz.tomorrow.rest.model.TomorrowActivity;
import com.ideaz.tomorrow.rest.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ITomorrowService {

    @GET("/api/v1/user")
    public void getUsers(Callback<List<User>> cb);

    @GET("/api/v1/activity")
    public void getActivities(Callback<List<TomorrowActivity>> cb);

    @POST("/api/v1/profile")
    public void saveProfile();

}
