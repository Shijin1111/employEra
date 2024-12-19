package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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


public class reportworker extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String url;
    ArrayList<String> name,lid;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1=findViewById(R.id.button15);
        l1=findViewById(R.id.lst);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportworker);
        RequestQueue queue = Volley.newRequestQueue(reportworker.this);
        url = "http://" + sh.getString("ip","") + ":5000/workerdetails_report";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {


                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    lid= new ArrayList<>();
//                            name= new ArrayList<>();
//                            place= new ArrayList<>();
//                            gender=new ArrayList<>();
//                            photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("FIRSTNAME"));
                        lid.add(jo.getString("LID"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                    l1.setAdapter(new custom2(reportworker.this,name,lid));
                    l1.setOnItemClickListener(reportworker.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(reportworker.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, ""+lid.get(i), Toast.LENGTH_SHORT).show();
        Intent ik = new Intent(getApplicationContext(), reason.class);
        ik.putExtra("lid",lid.get(i));
        startActivity(ik);
    }


}