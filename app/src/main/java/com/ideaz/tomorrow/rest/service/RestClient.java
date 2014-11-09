package com.ideaz.tomorrow.rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ideaz.tomorrow.rest.model.adapters.ItemTypeAdapterFactory;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {
    private static final String BASE_URL = "http://www.myserviceurl.com/";
    private ITomorrowService apiService;

    public RestClient()
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setRequestInterceptor(new SessionRequestInterceptor())
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ITomorrowService.class);
    }

    public ITomorrowService getApiService()
    {
        return apiService;
    }
}
