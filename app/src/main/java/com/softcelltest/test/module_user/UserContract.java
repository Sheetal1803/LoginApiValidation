package com.softcelltest.test.module_user;

import com.softcelltest.test.common.BasePresenter;
import com.softcelltest.test.common.BaseView;
import com.softcelltest.test.model.User;

import java.util.ArrayList;

public interface UserContract {

    interface View extends BaseView {

        void onArticleFound(ArrayList<User> usersList);

        void showNoUsersView();

        void hideNoUsersView();

        void onUsersNotFound(Throwable throwable);
    }

    interface Presenter extends BasePresenter {

        void fetchUsersList();

        void onDestroy();

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetUsersIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<User> userArrayList);
            void onResponseFailure();
            void onApiFailure(Throwable t);
        }

        void getUsers(OnFinishedListener onFinishedListener);
    }
}
