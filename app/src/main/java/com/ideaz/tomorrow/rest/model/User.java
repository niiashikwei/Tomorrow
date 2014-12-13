package com.ideaz.tomorrow.rest.model;

import com.google.gson.annotations.SerializedName;

public class User {

    private final String name;
    private final Integer age;
    @SerializedName("current_city")
    private final String currentCity;
    @SerializedName("profile_pic_url")
    private final String profilePicUrl;

    public User(String name, Integer age, String currentCity, String profilePicUrl) {
        this.name = name;
        this.age = age;
        this.currentCity = currentCity;
        this.profilePicUrl = profilePicUrl;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }
}
