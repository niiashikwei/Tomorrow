package com.ideaz.tomorrow.rest.model;

import com.google.gson.annotations.SerializedName;

public class User {

    private final String name;
    private final Integer age;

    @SerializedName("profile_pic_url")
    private final String profilePicUrl;

    public User(String name, Integer age, String profilePicUrl) {
        this.name = name;
        this.age = age;
        this.profilePicUrl = profilePicUrl;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }
}
