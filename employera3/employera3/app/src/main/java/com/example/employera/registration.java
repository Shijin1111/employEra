package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class registration extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
    RadioButton r1,r2;
    Button b1;
    String fname,lname,gender,dob,place,post,pin,email,phone,uname,passwd,url;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=findViewById(R.id.editTextTextPersonName3);
        e2=findViewById(R.id.editTextTextPersonName32);
        e3=findViewById(R.id.editTextTextPersonName33);
        e4=findViewById(R.id.editTextTextPersonName35);
        e5=findViewById(R.id.editTextTextPersonName36);
        e6=findViewById(R.id.editTextTextPersonName37);
        e7=findViewById(R.id.editTextTextPersonName38);
        e8=findViewById(R.id.editTextTextPersonName39);
        e9=findViewById(R.id.editTextTextPersonName40);
        e10=findViewById(R.id.editTextTextPersonName22);
        r1=findViewById(R.id.radioButton);
        r2=findViewById(R.id.radioButton2);
        b1=findViewById(R.id.button16);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = e1.getText().toString();
                lname = e2.getText().toString();
                gender = "";
                if (r1.isChecked()) {
                    gender = r1.getText().toString();
                } else {
                    gender = r2.getText().toString();
                }
                dob = e3.getText().toString();
                place = e10.getText().toString();
                post = e4.getText().toString();
                pin = e5.getText().toString();
                email = e6.getText().toString();
                phone = e7.getText().toString();
                uname = e8.getText().toString();
                passwd = e9.getText().toString();
                if (fname.equalsIgnoreCase("")) {
                    e1.setError("Enter your firstname");
                    e1.requestFocus();
                } else if (fname.matches("[a-zA-Z]")) {

                    e1.setError("characters allowed");
                } else if (lname.equalsIgnoreCase("")) {
                    e2.setError("Enter your lastname");
                    e2.requestFocus();
                } else if (lname.matches("[a-zA-Z]")) {

                    e2.setError("characters allowed");
                }else if (place.equalsIgnoreCase("")) {
                    e10.setError("Enter your place");
                    e10.requestFocus();
                } else if (place.matches("[a-zA-Z]")) {

                    e10.setError("characters allowed");
                } else if (lname.equalsIgnoreCase("")) {
                    e4.setError("Enter your post");
                    e4.requestFocus();
                } else if (lname.matches("[a-zA-Z]")) {

                    e4.setError("characters allowed");
                }else if (email.equalsIgnoreCase("")) {
                    e6.setError("Enter Your Email");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    e6.setError("Enter Valid Email");
                    e6.requestFocus();
                } else if (phone.equalsIgnoreCase("")) {
                    e7.setError("Enter Your Phone No");
                    e7.requestFocus();
                } else if (phone.length() != 10) {
                    e7.setError("Minimum 10 nos required");
                    e7.requestFocus();
                } else if (pin.equalsIgnoreCase("")) {
                    e5.setError("Enter Your pin");
                    e5.requestFocus();
                } else if (pin.length() != 6) {
                    e5.setError("Minimum 6 nos required");
                    e5.requestFocus();
                } else if (uname.equalsIgnoreCase("")) {
                    e9.setError("Enter your username");
                    e9.requestFocus();
                } else if (passwd.equalsIgnoreCase("")) {
                    e10.setError("Enter your password");
                    e10.requestFocus();
                } else {
                    RequestQueue queue = Volley.newRequestQueue(registration.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/usersignup";

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
                                    Toast.makeText(registration.this, "successfully registered", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), login.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(registration.this, "Error", Toast.LENGTH_SHORT).show();

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
                            params.put("fname", fname);
                            params.put("lname", lname);
                            params.put("gender", gender);
                            params.put("dob", dob);
                            params.put("place", place);
                            params.put("post", post);
                            params.put("pin", pin);
                            params.put("email", email);
                            params.put("phone", phone);

                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }

    });


}
}