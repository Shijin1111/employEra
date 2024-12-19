package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

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

public class addwork extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    Button b1;
    String work,details,place,pin,post,amount,date,time,url;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);
        e1=findViewById(R.id.editTextTextPersonName24);
        e2=findViewById(R.id.editTextTextPersonName25);
        e3=findViewById(R.id.editTextTextPersonName26);
        e4=findViewById(R.id.editTextTextPersonName27);
        e5=findViewById(R.id.editTextTextPersonName28);
        e6=findViewById(R.id.editTextTextPersonName13);
        e7=findViewById(R.id.editTextDate2);
        e8=findViewById(R.id.editTextTime3);
        b1=findViewById(R.id.button11);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                work=e1.getText().toString();
                details=e2.getText().toString();
                place=e3.getText().toString();
                pin=e4.getText().toString();
                post=e5.getText().toString();
                amount=e6.getText().toString();
                date=e7.getText().toString();
                time=e8.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(addwork.this);
                url = "http://" + sh.getString("ip","") + ":5000/sendjobdetails";

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

                                Toast.makeText(addwork.this, "Successfully added", Toast.LENGTH_SHORT).show();

                                Intent ik = new Intent(getApplicationContext(), userhome.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(addwork.this, "Invalid", Toast.LENGTH_SHORT).show();



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
                        params.put("lid",sh.getString("lid",""));
                        params.put("work",work);
                        params.put("details", details);
                        params.put("place", place);
                        params.put("post", post);
                        params.put("pin", pin);
                        params.put("amount", amount);
                        params.put("date",date);
                        params.put("time",time);


                        return params;
                    }
                };
                queue.add(stringRequest);

            }

        });


    }
}
