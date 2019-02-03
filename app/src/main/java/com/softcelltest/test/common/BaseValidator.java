package com.softcelltest.test.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseValidator {
    private String TAG = getClass().getSimpleName();

    public boolean isEmpty(String string) {
        return string.isEmpty() || string.equals("") || string.equalsIgnoreCase("null");
    }

    public boolean hasEnoughChars(String string, int length) {
        if (isEmpty(string))
            return false;
        return string.length() >= length;
    }

    public boolean isValidEmail(String email) {
        if (email == null || isEmpty(email))
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public boolean firstnameEmpty(String firtstName) {
        if (firtstName == null || isEmpty(firtstName)) {
            return false;
        } else return true;
    }

    public boolean lastnameEmpty(String lastName) {
        if (lastName == null || isEmpty(lastName)) {
            return false;
        } else return true;
    }

    public boolean isValidPanCard(String panCard) {
        if (panCard == null || isEmpty(panCard))
            return false;

        Pattern mPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher mMatcher = mPattern.matcher(panCard);
        return mMatcher.matches();
    }

    public boolean isValidVoterID(String voterId) {
        if (voterId == null || isEmpty(voterId))
            return false;

        Pattern mPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher mMatcher = mPattern.matcher(voterId);
        return mMatcher.matches();
    }

    public boolean isValidAadharNumber(String aadharNumber) {
        return hasEnoughChars(aadharNumber, 12);
    }

    public boolean idValidLoanAmount(String loanAmount) {
        if (loanAmount == null || isEmpty(loanAmount))
            return false;

        return true;
    }

    public boolean rangeLoanAmount(String loanAmount) {
        if (loanAmount == null || isEmpty(loanAmount))
            return false;
        if (Long.parseLong(loanAmount) > 100000)
            return false;
        return true;
    }

}
