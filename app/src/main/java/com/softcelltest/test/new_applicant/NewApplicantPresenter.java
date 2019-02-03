package com.softcelltest.test.new_applicant;


import android.text.TextUtils;

import com.softcelltest.test.R;
import com.softcelltest.test.common.BaseValidator;

public class NewApplicantPresenter extends BaseValidator implements NewApplicantContract.Presenter {
    NewApplicantContract.View view;

    public NewApplicantPresenter(NewApplicantContract.View view) {
        this.view = view;
    }


    @Override
    public boolean onValidCheck() {
        if (!firstnameEmpty(view.getFirtstName())) {
            view.setFirstNameError(R.string.enter_firstname);
            return false;
        } else if (!lastnameEmpty(view.getLastName())) {
            view.setLastNameError(R.string.enter_lastname);
            return false;
        } else if (!isValidEmail(view.getEmailId())) {
            view.setEmailidError(R.string.enter_valid_emailid);
            return false;
        } else if (!idValidLoanAmount(view.getLoanAmount())) {
            view.setLoanAmntError(R.string.enter_valid_loanAmount);
            return false;
        }else if (!rangeLoanAmount(view.getLoanAmount())) {
            view.setLoanAmntError(R.string.enter_range_loanAmount);
            return false;
        }
        long loan = Long.parseLong(view.getLoanAmount());
        if (loan < 30000) {
            if (!(TextUtils.isEmpty(view.getPanCard()) && TextUtils.isEmpty(view.getAadhaarNumber()) && TextUtils.isEmpty(view.getVoterID()))) {
                if (!TextUtils.isEmpty(view.getPanCard())) {
                    if (!isValidPanCard(view.getPanCard())) {
                        view.setPanCardError(R.string.enter_valid_pancard);
                        return false;
                    }
                } else if (!TextUtils.isEmpty(view.getAadhaarNumber())) {
                    if (!isValidAadharNumber(view.getAadhaarNumber())) {
                        view.setAadharError(R.string.enter_valid_aadharNumber);
                        return false;
                    }
                } else if (!TextUtils.isEmpty(view.getVoterID())) {
                    if (!isValidVoterID(view.getVoterID())) {
                        view.setVoteridError(R.string.enter_valid_voterId);
                        return false;
                    }
                }
                return true;
            } else {
                view.showToast("Enter Pan card/Aadhar number/Voter ID");
                return false;
            }
        } else if (loan >= 30000 && loan < 50000) {

            if (!(TextUtils.isEmpty(view.getPanCard()) && TextUtils.isEmpty(view.getAadhaarNumber()))) {
                if (!TextUtils.isEmpty(view.getPanCard())) {
                    if (!isValidPanCard(view.getPanCard())) {
                        view.setPanCardError(R.string.enter_valid_pancard);
                        return false;
                    }
                } else if (!TextUtils.isEmpty(view.getAadhaarNumber())) {
                    if (!isValidAadharNumber(view.getAadhaarNumber())) {
                        view.setAadharError(R.string.enter_valid_aadharNumber);
                        return false;
                    }
                }
                return true;
            } else {
                view.showToast("Enter Pan card/Aadhar number");
                return false;
            }

        } else if (loan >= 50000 && loan < 100000) {
            if (!isValidPanCard(view.getPanCard())) {
                view.setPanCardError(R.string.enter_valid_pancard);
                return false;
            }
            return true;
        }
        return true;
    }


    @Override
    public void onDestroy() {

    }
}
