package com.ashkansajadi.clubinvite;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InviteApi {

    String apiUrl = "https://club.xnull.xyz/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(".")
    Call<InviteResponse> getInvitation(@Query("phone") String phoneNumber);

}