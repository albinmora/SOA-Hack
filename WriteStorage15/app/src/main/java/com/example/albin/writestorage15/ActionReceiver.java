package com.example.albin.writestorage15;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.example.albin.writestorage15.writestorage.WriteStorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActionReceiver extends BroadcastReceiver {
    static final String FILENAME = "NAME";
    static final String FILEPATH = "PATH";
    static final String FILECONTENT = "CONTENT";
    File mExternalFile;
    String mTextWrite;
    private String mFilename = "SampleFile.txt";
    private String mFilepath;


    /**
     * Write in disk with the values that the user
     * send whit StringExtra in a Intend element.
     *
     * @param context Context of the Activity
     * @param intent  Intent that call this method
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        mFilepath = intent.getStringExtra(FILEPATH);
        mFilename = intent.getStringExtra(FILENAME);
        mTextWrite = intent.getStringExtra(FILECONTENT);

        if (!WriteStorage.isExternalStorageAvailable() || WriteStorage.isExternalStorageReadOnly()) {
            System.out.print("No se puede conectar");
        } else {
            mExternalFile = new File(context.getExternalFilesDir(mFilepath), mFilename);
            try {
                FileOutputStream fos = new FileOutputStream(mExternalFile);
                fos.write(mTextWrite.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
