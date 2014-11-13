package com.ideaz.tomorrow.rest.service;

import android.util.Log;

import retrofit.RequestInterceptor;

public class SessionRequestInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        Log.i("network:SessionRequestInterceptor", request.toString());
    }
}
