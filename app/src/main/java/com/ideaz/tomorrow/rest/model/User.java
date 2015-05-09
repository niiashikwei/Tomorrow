package com.ideaz.tomorrow.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    public static String USER_ID = "userId";
    public static int NO_LOCAL_USER = 0;
    private final String name;
    private final Integer age;
    @SerializedName("current_city")
    private final String currentCity;
    @SerializedName("profile_pic_url")
    private final String profilePicUrl;
    private Integer id;

    public User(String name, Integer age, String currentCity, String profilePicUrl) {
        this.name = name;
        this.age = age;
        this.currentCity = currentCity;
        this.profilePicUrl = profilePicUrl;
    }

    public User(String name, Integer age, String currentCity, String profilePicUrl, Integer id) {
        this.name = name;
        this.age = age;
        this.currentCity = currentCity;
        this.profilePicUrl = profilePicUrl;
        this.id = id;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
