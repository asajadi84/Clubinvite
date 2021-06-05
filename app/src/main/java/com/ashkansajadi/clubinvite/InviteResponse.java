package com.ashkansajadi.clubinvite;

import com.google.gson.annotations.SerializedName;

public class InviteResponse {

    @SerializedName("Code")
    private int code;

    @SerializedName("phone")
    private String phone;

    @SerializedName("Condition")
    private String condition;

    @SerializedName("RunTime")
    private String runTime;

    @SerializedName("Channel")
    private String channel;

    @SerializedName("Developer")
    private String developer;

    public String getMsg(){
        return "شماره همراه " + phone + " با وضعیت " + condition + " در مدت زمان " + runTime + " در سرور ثبت شد.";
    }

}
