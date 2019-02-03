package com.softcelltest.test.network.api_service;

import com.softcelltest.test.model.LoginResponse;
import com.softcelltest.test.model.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("api/users?page=2")
    Call<UsersResponse> getUsersResponse();

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> doLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
