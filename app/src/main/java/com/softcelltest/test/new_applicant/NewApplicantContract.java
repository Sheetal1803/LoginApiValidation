package com.softcelltest.test.new_applicant;


import com.softcelltest.test.common.BasePresenter;
import com.softcelltest.test.common.BaseView;

public class NewApplicantContract {
    public interface View extends BaseView {
        String getAadhaarNumber();

        String getFirtstName();

        String getLastName();

        String getEmailId();

        String getPanCard();

        String getLoanAmount();

        String getVoterID();

        void setEmailidError(int resId);

        void setAadharError(int resId);

        void setPanCardError(int resId);

        void setLoanAmntError(int resId);

        void setVoteridError(int resId);

        void setFirstNameError(int resId);

        void setLastNameError(int resId);
    }

    public interface Presenter extends BasePresenter {

        boolean onValidCheck();
    }
}
