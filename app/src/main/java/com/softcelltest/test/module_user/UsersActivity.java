package com.softcelltest.test.module_user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.softcelltest.test.R;
import com.softcelltest.test.adapter.UsersAdapter;
import com.softcelltest.test.common.BaseActivity;
import com.softcelltest.test.model.User;
import com.softcelltest.test.new_applicant.NewApplicant;
import com.softcelltest.test.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;

public class UsersActivity extends BaseActivity implements UserContract.View, View.OnClickListener {

    private String TAG = "UsersActivity";
    private Context mContext;

    private UserContract.Presenter presenter;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.fabAdd)
    public FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mContext = this;

        initRecyclerView();

        if (presenter == null) {
            presenter = new UsersPresenterImpl(this, new GetUsersInteractorImpl());
            if(Utility.isNetworkAvailable(mContext)) {
                fetchingData();
            }else{
                showToast(R.string.internet_error);
            }

        }

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        fabAdd.setOnClickListener(this);
    }


    @Override
    public void onArticleFound(ArrayList<User> usersList) {
        dismissProgressDialog();
        Log.e(TAG, "onArticleFound: "+usersList.size());

        UsersAdapter mAdapter = new UsersAdapter(mContext, usersList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showNoUsersView() {
        dismissProgressDialog();
        showToast(R.string.no_user_found);
    }

    @Override
    public void hideNoUsersView() {
        dismissProgressDialog();
        showToast(R.string.no_user_found);
    }

    @Override
    public void onUsersNotFound(Throwable throwable) {
        dismissProgressDialog();
        showToast(throwable.getMessage());
    }

    private void fetchingData() {
        if (hasConnectivity()) {
            showProgressDialog("Fetching Users..");
            presenter.fetchUsersList();
        } else {
            showToast(R.string.no_internet);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fabAdd:
                startActivity(new Intent(UsersActivity.this,NewApplicant.class));
                break;
        }
    }
}
