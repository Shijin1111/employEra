package com.example.employera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2, b3;
    SharedPreferences sh;
    String uname, passwd, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName2);
        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button6);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = e1.getText().toString();
                passwd = e2.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(login.this);
                url = "http://" + sh.getString("ip", "") + ":5000/login";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {
                                String lid = json.getString("lid");
                                String type = json.getString("type");
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("lid", lid);
                                edp.commit();
                                if (type.equalsIgnoreCase("user")) {
                                    Toast.makeText(login.this, "WELCOME", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), userhome.class);
                                    startActivity(ik);
                                } else {
                                    Toast.makeText(login.this, "welcome", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), workerhome.class);
                                    startActivity(ik);
                                }


                            } else {

                                Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("uname", uname);
                        params.put("pswd", passwd);

                        return params;
                    }
                };
                queue.add(stringRequest);

            }

        });


        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      AlertDialog.Builder ald = new AlertDialog.Builder(login.this);
                                      ald.setTitle("choose your type")
                                              .setPositiveButton(" worker registration", new DialogInterface.OnClickListener() {

                                                  @Override
                                                  public void onClick(DialogInterface arg0, int arg1) {
                                                      try {

                                                          Intent i = new Intent(getApplicationContext(), workerregistration.class);
                                                          startActivity(i);


                                                      } catch (Exception e) {
                                                          Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                                                      }

                                                  }
                                              })
                                              .setNegativeButton(" user registration ", new DialogInterface.OnClickListener() {

                                                  @Override
                                                  public void onClick(DialogInterface arg0, int arg1) {


                                                      Intent i = new Intent(getApplicationContext(), registration.class);
                                                      startActivity(i);

                                                  }
                                              });


                                      AlertDialog al = ald.create();
                                      al.show();


                                  }
                              }

        );
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }
}


