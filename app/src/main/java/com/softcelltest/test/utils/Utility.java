package com.softcelltest.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Utility {
    public static boolean isNetworkAvailable(Context context) {
        boolean haveConnectedMobile = false;
        boolean haveConnectedWifi = false;
        try {
            haveConnectedMobile = false;
            haveConnectedWifi = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            for (NetworkInfo network : networkInfo) {
                if (network.getTypeName().equalsIgnoreCase("WIFI")) {
                    if (network.isConnected()) {
                        haveConnectedWifi = true;
                    }
                }
                if (network.getTypeName().equalsIgnoreCase("MOBILE")) {
                    if (network.isConnected()) {
                        haveConnectedMobile = true;
                    }
                }
            }
            Log.i("isNetworkAvailable", ":"
                    + (haveConnectedWifi || haveConnectedMobile));
        } catch (Exception e) {
            return false;
        }
        return haveConnectedWifi || haveConnectedMobile;

    }
}
