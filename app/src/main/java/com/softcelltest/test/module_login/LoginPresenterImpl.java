package com.softcelltest.test.module_login;

import android.text.TextUtils;

import com.softcelltest.test.R;
import com.softcelltest.test.model.LoginResponse;

public class LoginPresenterImpl implements LoginContract.Presenter,LoginContract.LoginIntractor.OnFinishedListener {


    private LoginContract.View mainView;
    private LoginContract.LoginIntractor loginIntractor;

    public LoginPresenterImpl(LoginContract.View view, LoginContract.LoginIntractor loginIntractor) {
        this.mainView = view;
        this.loginIntractor = loginIntractor;
    }

    @Override
    public void sendLoginRequest(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            mainView.setUserNameError(R.string.err_username);
        } else if (TextUtils.isEmpty(password)) {
            mainView.setPasswordError(R.string.err_password);
        } else  {
            mainView.showProgress("Verifying user...");
            loginIntractor.doLogin(username,password,this);
        }

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(LoginResponse loginResponse) {
        if(mainView != null){
            mainView.showLoginSuccess();
            mainView.hideProgress();
        }
    }

    @Override
    public void onResponseFailure() {
        if(mainView != null){
            mainView.showLoginFailure();
            mainView.hideProgress();
        }
    }

    @Override
    public void onApiFailure(Throwable t) {
        if(mainView != null){
            mainView.onInvalidLogin(t);
            mainView.hideProgress();
        }
    }
}
