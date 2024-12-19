package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addrating extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1;
    Button b1;
    RatingBar r1;
    String rating,url,worker;
    SharedPreferences sh;

    ArrayList<String> name,wrid;

    String worker_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrating);
        s1=findViewById(R.id.spinner);
        r1=findViewById(R.id.ratingBar);
        b1=findViewById(R.id.button6);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        url ="http://"+sh.getString("ip", "") + ":5000/viewworkdone";
        RequestQueue queue = Volley.newRequestQueue(addrating.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {


                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    wrid= new ArrayList<>();


//                            name= new ArrayList<>();
//                            place= new ArrayList<>();
//                            gender=new ArrayList<>();
//                            photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("FIRSTNAME")+ " "+jo.getString("LASTNAME"));
                        wrid.add(jo.getString("wid"));

//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(addrating.this,android.R.layout.simple_list_item_1,name);
                    s1.setAdapter(ad);

                    s1.setOnItemSelectedListener(addrating.this);

//                    l1.setAdapter(new custom4(addrating.this,name,place,E_mail,phone));
//                            l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(addrating.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id",sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating=String.valueOf(r1.getRating());

                RequestQueue queue = Volley.newRequestQueue(addrating.this);
                url = "http://" + sh.getString("ip", "") + ":5000/addrating";

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
                                Toast.makeText(addrating.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                Intent ik = new Intent(getApplicationContext(), userhome.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(addrating.this, "not valid", Toast.LENGTH_SHORT).show();

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

                        params.put("rating", rating);
                        params.put("worker", worker_id);


                        return params;
                    }
                };
                queue.add(stringRequest);

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        worker_id = wrid.get(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
