package com.example.ecommerceapps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkConnectivity {
    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        // should check null because in airplane mode it will be null
        return netInfo != null && netInfo.isConnected();
    }

    public static final String TAG = "NetworkConnectivity";

    public static boolean checkConnection(Context context) {
        if (context == null) return false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        try {
            if (wifi != null && wifi.isConnected() || mobile != null && mobile.isConnected()) {
                // Do something
                Log.i(TAG, "Network is Available. either wifi or mobiledata ");
                return true;
            }
        } catch (Exception e) {
            Log.i(TAG, "Network Exception: please check network connectivity ");
        }
        return false;
    }

    public static int connectionStatus(Context context) {
        int wif_data = 0;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isAvailable() && nInfo.isConnected()) {
            if (nInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                wif_data = 1;
                Log.i(TAG, "Network status: ConnectivityManager.TYPE_WIFI ");
            } else if (nInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                wif_data = 2;
                Log.i(TAG, "Network status: ConnectivityManager.TYPE_MOBILE ");
            }
        }
        return wif_data;
    }
}

