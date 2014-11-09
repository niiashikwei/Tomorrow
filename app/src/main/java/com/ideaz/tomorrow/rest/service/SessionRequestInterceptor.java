package com.ideaz.tomorrow.rest.service;

import retrofit.RequestInterceptor;

public class SessionRequestInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        if (true)
        request.addHeader("Header name", "Header Value");
    }
}
