package com.softcelltest.test.new_applicant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softcelltest.test.R;
import com.softcelltest.test.common.BaseActivity;

import butterknife.BindView;

public class NewApplicant extends BaseActivity implements NewApplicantContract.View, View.OnClickListener {

    @BindView(R.id.emailid_edit)
    EditText emailid_edit;
    @BindView(R.id.aadhar_edit)
    EditText aadhar_edit;
    @BindView(R.id.firstname_edit)
    EditText firstname_edit;
    @BindView(R.id.lastname_edit)
    EditText lastname_edit;
    @BindView(R.id.pannumber_edit)
    EditText pannumber_edit;
    @BindView(R.id.voterid_edit)
    EditText voterid_edit;
    @BindView(R.id.loanamount_edit)
    EditText loanamount_edit;
    @BindView(R.id.add_button)
    Button add_button;

    NewApplicantContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_applicant);

        if (presenter == null)
            presenter = new NewApplicantPresenter(NewApplicant.this);

        add_button.setOnClickListener(this);

    }


    @Override
    public String getAadhaarNumber() {
        return aadhar_edit.getText().toString();
    }

    @Override
    public String getFirtstName() {
        return firstname_edit.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastname_edit.getText().toString();
    }

    @Override
    public String getEmailId() {
        return emailid_edit.getText().toString();
    }

    @Override
    public String getPanCard() {
        return pannumber_edit.getText().toString();
    }

    @Override
    public String getLoanAmount() {
        return loanamount_edit.getText().toString();
    }

    @Override
    public String getVoterID() {
        return voterid_edit.getText().toString();
    }

    @Override
    public void setEmailidError(int resId) {
        emailid_edit.setError(getString(resId));
    }

    @Override
    public void setAadharError(int resId) {
        aadhar_edit.setError(getString(resId));
    }

    @Override
    public void setPanCardError(int resId) {
        pannumber_edit.setError(getString(resId));
    }

    @Override
    public void setLoanAmntError(int resId) {
        loanamount_edit.setError(getString(resId));
    }

    @Override
    public void setVoteridError(int resId) {
        voterid_edit.setError(getString(resId));
    }

    @Override
    public void setFirstNameError(int resId) {
        firstname_edit.setError(getString(resId));
    }

    @Override
    public void setLastNameError(int resId) {
        lastname_edit.setError(getString(resId));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add_button:
                if (presenter.onValidCheck()) {
                    showToast("Applicant added Success.");
                }
                break;

        }

    }

}
