package com.ideaz.tomorrow.rest.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.Getter;
import lombok.Setter;

@Parcel
public class User {
    @SerializedName("name")
    @Getter @Setter
    private String name;

    @SerializedName("age")
    @Getter @Setter
    private Integer age;

    @SerializedName("profilePicUrl")
    @Getter @Setter
    private String profilePicUrl;
}
