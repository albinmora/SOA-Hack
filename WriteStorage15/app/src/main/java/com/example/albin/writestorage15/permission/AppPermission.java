package com.example.albin.writestorage15.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class AppPermission {

    public static final int MY_PERMISSIONS_REQUEST_CODE = 1;


    /**
     * Boolean method that check if  WRITE_EXTERNAL_STORAGE
     * permission is Granted or Denied.
     *
     * @return true, false  WRITE_EXTERNAL_STORAGE is Granted, Denied
     */
    public static boolean checkPermissions(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * Request for WRITE_EXTERNAL_STORAGE to
     * user of app.
     *
     * @see  ActivityCompat with requestPermissions
     */
    public static void setPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                AppPermission.MY_PERMISSIONS_REQUEST_CODE);
    }
}
