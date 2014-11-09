package com.ideaz.tomorrow.rest.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.Getter;
import lombok.Setter;

@Parcel
public class RestError {
    @SerializedName("code")
    @Getter @Setter
    private Integer code;

    @SerializedName("error_message")
    @Getter
    @Setter
    private String strMessage;

    public RestError(){}

    public RestError(String strMessage) {
        this.strMessage = strMessage;
    }

}