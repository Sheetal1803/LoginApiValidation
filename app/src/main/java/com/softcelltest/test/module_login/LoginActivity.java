package com.softcelltest.test.module_login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softcelltest.test.R;
import com.softcelltest.test.common.BaseActivity;
import com.softcelltest.test.module_user.UsersActivity;
import com.softcelltest.test.utils.Utility;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginContract.View {

    private String TAG = "LoginActivity";
    private Context mContext;

    @BindView(R.id.et_username)
    EditText editUserName;
    @BindView(R.id.et_password)
    EditText editPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        init();
        loginPresenter = new LoginPresenterImpl(this, new LoginIntractorImpl());
    }

    private void init() {
        btnLogin.setOnClickListener(this);
    }


    @Override
    public String getUserName() {
        return editUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return editPassword.getText().toString().trim();
    }

    @Override
    public void setUserNameError(int resId) {
        editUserName.setError(getString(resId));
    }

    @Override
    public void setPasswordError(int resId) {
        editPassword.setError(getString(resId));
    }

    @Override
    public void showLoginSuccess() {
        Intent intent  = new Intent(mContext, UsersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginFailure() {
        showToast(getString(R.string.str_failed_login));
    }

    @Override
    public void showProgress(String msg) {
        showProgressDialog(msg);}

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }


    @Override
    public void onInvalidLogin(Throwable throwable) {
        showToast(throwable.getMessage());
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                if(Utility.isNetworkAvailable(mContext)) {
                    loginPresenter.sendLoginRequest(getUserName(), getPassword());
                }else{
                    showToast(R.string.internet_error);
                }
                break;
        }
    }


}
