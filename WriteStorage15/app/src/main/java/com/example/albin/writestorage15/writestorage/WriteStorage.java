package com.example.albin.writestorage15.writestorage;

import android.os.Environment;

public class WriteStorage {
    /**
     * Validate if the externalStorage, SDCARD,
     * is readOnly or not.
     *
     * @return true or false if external storage has Read Only permission
     */
    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    /**
     * Validate if the externalStorage, SDCARD,
     * is available to be use
     *
     * @return true or false if external storage is successfully mounted
     */
    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
