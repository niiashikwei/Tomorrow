package com.ideaz.tomorrow.rest.model;

import java.util.List;

import lombok.Data;

import static org.parceler.guava.collect.Lists.newArrayList;

public class TomorrowActivity {
    private final String name;
    private final Integer teamSize;

    public TomorrowActivity(String name, Integer teamSize) {
        this.name = name;
        this.teamSize = teamSize;
    }

    public static List<String> getActivityNameList(List<TomorrowActivity> activities) {
        List<String> list = newArrayList();
        for (TomorrowActivity activity : activities) {
            list.add(activity.getName());
        }
        return list;
    }

    public String getName() {
        return name;
    }
}
