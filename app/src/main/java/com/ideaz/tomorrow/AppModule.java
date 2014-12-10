package com.ideaz.tomorrow;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ideaz.tomorrow.activities.ActivitySelectorActivity;
import com.ideaz.tomorrow.activities.PartnerSelectionActivity;
import com.ideaz.tomorrow.activities.ProfileActivity;
import com.ideaz.tomorrow.rest.model.adapters.ItemTypeAdapterFactory;
import com.ideaz.tomorrow.rest.service.ITomorrowService;
import com.ideaz.tomorrow.rest.service.SessionRequestInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module(
        injects = {
                TomorrowApp.class,
                PartnerSelectionActivity.class,
                ProfileActivity.class,
                ActivitySelectorActivity.class
        },
        library = true
)
public class AppModule {
    private static final String BASE_URL = "http://10.0.2.2:3000";

    private TomorrowApp tomorrowApp;

    public AppModule(TomorrowApp tomorrowApp) {
        this.tomorrowApp = tomorrowApp;
    }

    @Provides @Singleton
    public Context provideApplicationContext() {
        return tomorrowApp;
    }

    @Provides @Singleton
    public ITomorrowService provideITomorrowService()
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