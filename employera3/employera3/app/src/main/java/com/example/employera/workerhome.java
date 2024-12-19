package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class workerhome extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerhome);
        b1=findViewById(R.id.button18);
        b2=findViewById(R.id.button17);
        b3=findViewById(R.id.button19);
        b4=findViewById(R.id.button20);
        b5=findViewById(R.id.button21);
        b6=findViewById(R.id.button28);
        b7=findViewById(R.id.button29);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addskills.class);
                startActivity(i);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),bidbyworker.class);
                Intent i=new Intent(getApplicationContext(),employeeviewwork.class);
                startActivity(i);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),chatwithworker.class);
                startActivity(i);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),reportuser.class);
                startActivity(i);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),sendfeedback.class);
                startActivity(i);

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),jobstatus.class);
                startActivity(i);

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),login.class);
        startActivity(i);

    }
}