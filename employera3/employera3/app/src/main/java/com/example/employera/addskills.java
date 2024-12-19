package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class addskills extends AppCompatActivity {
    Button b1;
    ListView l1;
    SharedPreferences sh;
    ArrayList<String>skills;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addskills);
        b1=findViewById(R.id.button15);
        l1=findViewById(R.id.list55);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/addskills";
        RequestQueue queue = Volley.newRequestQueue(addskills.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {


                    JSONArray ar=new JSONArray(response);
                    skills= new ArrayList<>();
//                            name= new ArrayList<>();
//                            place= new ArrayList<>();
//                            gender=new ArrayList<>();
//                            photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        skills.add(jo.getString("skills"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(addskills.this,android.R.layout.simple_list_item_1,skills);
                    l1.setAdapter(ad);

//                            l1.setAdapter(new Custom(viewuser.this,name,place));
//                            l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(addskills.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),addingskills.class);
                startActivity(i);




            }
        });
    }
}