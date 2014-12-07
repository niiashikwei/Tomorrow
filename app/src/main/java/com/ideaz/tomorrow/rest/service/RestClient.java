package com.ideaz.tomorrow.rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ideaz.tomorrow.rest.model.adapters.ItemTypeAdapterFactory;

import org.parceler.javaxinject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module(
        injects = ITomorrowService.class,
        library = false
)
public class RestClient {
    private static final String BASE_URL = "http://10.0.2.2:3000";

    @Provides @Singleton
    public ITomorrowService getApiService()
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

        return restAdapter.create(ITomorrowService.class);
    }
}
