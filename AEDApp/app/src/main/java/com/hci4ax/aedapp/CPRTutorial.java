package com.hci4ax.aedapp;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CPRTutorial extends AppCompatActivity {

    // enable the vibration in this activity
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprtutorial);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }
}
