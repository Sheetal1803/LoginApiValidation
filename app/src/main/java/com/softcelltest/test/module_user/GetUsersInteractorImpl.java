package com.softcelltest.test.module_user;

import com.softcelltest.test.model.UsersResponse;
import com.softcelltest.test.network.api_service.ApiInterface;
import com.softcelltest.test.network.client.ApiClient;
import com.softcelltest.test.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUsersInteractorImpl implements UserContract.GetUsersIntractor {

    public static final String TAG = "GetUsers";

    @Override
    public void getUsers(final OnFinishedListener onFinishedListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call call = apiService.getUsersResponse();

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                LogUtil.info(TAG, "onResponse ->" + response.body());
                LogUtil.info(TAG, "onResponse Users Size ->" + response.body().getUsers());
                if (response.body() != null) {
                    if (response.body().getUsers() != null && response.body().getUsers().size() != 0) {
                        onFinishedListener.onFinished(response.body().getUsers());
                    } else {
                        onFinishedListener.onResponseFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                onFinishedListener.onApiFailure(t);
            }
        });
    }
}
