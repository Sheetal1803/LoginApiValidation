package com.softcelltest.test.module_login;

import com.softcelltest.test.model.LoginResponse;
import com.softcelltest.test.network.api_service.ApiInterface;
import com.softcelltest.test.network.client.ApiClient;
import com.softcelltest.test.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginIntractorImpl implements LoginContract.LoginIntractor {

    public static final String TAG = "Login";

    @Override
    public void doLogin(String username, String password,final OnFinishedListener onFinishedListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call call = apiService.doLogin(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LogUtil.info(TAG, "onResponse ->" + response.body());
               // LogUtil.info(TAG, "onResponse  ->" + response.body().getToken());
                if (response.body() != null && response.body().getToken() != null) {
                    onFinishedListener.onFinished(response.body());

                } else {
                    onFinishedListener.onResponseFailure();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                onFinishedListener.onApiFailure(t);
            }
        });
    }
}
