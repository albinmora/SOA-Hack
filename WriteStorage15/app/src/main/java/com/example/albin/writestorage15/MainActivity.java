package com.example.albin.writestorage15;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.albin.writestorage15.permission.AppPermission;
import com.example.albin.writestorage15.writestorage.WriteStorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends android.app.Activity {

    EditText mInputText;
    TextView mResponse;
    Button mSaveButton;
    private String filename = "SampleFile.txt";
    private String filepath = "DIR";
    File mExternalFile;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputText = (EditText) findViewById(R.id.myInputText);
        mResponse = (TextView) findViewById(R.id.response);

        mSaveButton =
                (Button) findViewById(R.id.saveExternalStorage);
        mSaveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppPermission.checkPermissions((Activity)getApplicationContext())) {
                    try {
                        FileOutputStream fos = new FileOutputStream(mExternalFile);
                        fos.write(mInputText.getText().toString().getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mInputText.setText("");
                    mResponse.setText("SampleFile.txt saved to External Storage...");
                } else{
                    AppPermission.setPermissions((Activity)getApplicationContext());
                }
            }
        });

        if (!WriteStorage.isExternalStorageAvailable() || WriteStorage.isExternalStorageReadOnly()) {
            mSaveButton.setEnabled(false);
        } else {
            mExternalFile = new File(getExternalFilesDir(filepath), filename);
        }
    }

    /**
     * This method is executed when the user decided
     * if accept or not the WRITE_EXTERNAL_STORAGE
     * permission. Depend of the user choose the code
     * is going to show a Toast with the result of the
     * election.
     *
     * @param  requestCode code of the request permission
     * @param permissions  array with all permissions that the user accept or denied
     * @param grantResults array with the result of user's choose.
     * @see   Toast with the rusult message.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != AppPermission.MY_PERMISSIONS_REQUEST_CODE) {
            return;
        }
        boolean isGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }
        Toast toast;
        int message;

        if (isGranted) {
            message = R.string.permission_accepted;
        } else {
            message = R.string.permission_refused;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}

