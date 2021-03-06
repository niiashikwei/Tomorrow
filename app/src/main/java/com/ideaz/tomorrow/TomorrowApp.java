package com.ideaz.tomorrow;

import android.app.Application;
import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class TomorrowApp extends Application {
    public static final String SETTINGS = "tomorrow_settings";
    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
