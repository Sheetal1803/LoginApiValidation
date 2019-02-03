package com.softcelltest.test.module_user;

import com.softcelltest.test.model.User;
import java.util.ArrayList;


public class UsersPresenterImpl implements UserContract.Presenter,UserContract.GetUsersIntractor.OnFinishedListener {

    private UserContract.View mainView;
    private UserContract.GetUsersIntractor userIntractor;

    public UsersPresenterImpl(UserContract.View view, UserContract.GetUsersIntractor userIntractor) {
        this.mainView = view;
        this.userIntractor = userIntractor;
    }


    @Override
    public void fetchUsersList() {
        userIntractor.getUsers(this);
    }


    @Override
    public void onDestroy() {
        mainView = null;
    }


    @Override
    public void onFinished(ArrayList<User> userArrayList) {
        if(mainView != null){
            mainView.onArticleFound(userArrayList);
            mainView.dismissProgressDialog();
        }
    }


    @Override
    public void onResponseFailure() {
        if(mainView != null){
            mainView.showNoUsersView();
            mainView.dismissProgressDialog();
        }
    }

    @Override
    public void onApiFailure(Throwable t) {
        if(mainView != null){
            mainView.onUsersNotFound(t);
            mainView.dismissProgressDialog();
        }
    }


}
