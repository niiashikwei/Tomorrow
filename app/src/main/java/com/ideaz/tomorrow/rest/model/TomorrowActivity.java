package com.ideaz.tomorrow.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static org.parceler.guava.collect.Lists.newArrayList;

public class TomorrowActivity {
    public static String Tomorrow_Activity_ID = "tomorrowActivityId";
    private final String name;
    @SerializedName("min_team_size")
    private final Integer minTeamSize;
    @SerializedName("max_team_size")
    private final Integer maxTeamSize;
    private Integer id;

    public TomorrowActivity(String name, Integer minTeamSize, Integer maxTeamSize) {
        this.name = name;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    public TomorrowActivity(String name, Integer minTeamSize, Integer maxTeamSize, Integer id) {
        this.name = name;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
        this.id = id;
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

    public Integer getId() {
        return id;
    }
}
