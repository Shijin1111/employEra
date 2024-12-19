package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class viewfeedback extends AppCompatActivity {
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedback);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}