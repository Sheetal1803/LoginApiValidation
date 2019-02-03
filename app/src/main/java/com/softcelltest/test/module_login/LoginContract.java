package com.softcelltest.test.module_login;

import com.softcelltest.test.common.BasePresenter;
import com.softcelltest.test.common.BaseView;
import com.softcelltest.test.model.LoginResponse;

public interface LoginContract {

    interface View extends BaseView {

        String getUserName();
        String getPassword();

        void setUserNameError(int resId);
        void setPasswordError(int resId);

        void showLoginSuccess();
        void showLoginFailure();

       void showProgress(String msg);
        void hideProgress();
        void onInvalidLogin(Throwable throwable);
    }

    interface Presenter extends BasePresenter {

        void sendLoginRequest(String username, String password);

        void onDestroy();

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface LoginIntractor {

        interface OnFinishedListener {
            void onFinished(LoginResponse loginResponse);
            void onResponseFailure();
            void onApiFailure(Throwable t);
        }

        void doLogin(String userName, String password,LoginIntractor.OnFinishedListener onFinishedListener);
    }

}
