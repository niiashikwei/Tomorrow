package com.ideaz.tomorrow.rest.service;

import com.ideaz.tomorrow.rest.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ITomorrowService {

    @GET("/api/v1/user")
    public void getUsers(Callback<List<User>> cb);

}
