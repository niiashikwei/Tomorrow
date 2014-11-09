package com.ideaz.tomorrow.rest.service;

import com.ideaz.tomorrow.rest.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ITomorrowService {

    @GET("/api/nearby/users/userId/{userId}/long/{long}/lat/{lat}")
    public void getNearbyUsers(@Path("long") Long userId, @Path("long") Long longitude, @Path("lat") Long latitude, Callback<List<User>> cb);

}
