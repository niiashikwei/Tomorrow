package com.ideaz.tomorrow.rest.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class RestError {
    @SerializedName("code")
    protected Integer code;

    @SerializedName("error_message")
    protected String strMessage;

    public RestError(){}

    public RestError(String strMessage) {
        this.strMessage = strMessage;
    }

}