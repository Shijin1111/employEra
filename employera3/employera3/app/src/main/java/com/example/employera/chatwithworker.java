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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chatwithworker extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String url,ttid;
    ArrayList<String>name,place,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwithworker);
        l1=findViewById(R.id.ls1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/chatwithuser";
        RequestQueue queue = Volley.newRequestQueue(chatwithworker.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    uid= new ArrayList<>();
                    name= new ArrayList<>();
//                    place= new ArrayList<>();
//                    gender=new ArrayList<>();
//                    photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        uid.add(jo.getString("LID"));
                        name.add(jo.getString("FIRSTNAME")+jo.getString("LASTNAME"));
//                        place.add(jo.getString("PLACE"));


                    }

                     ArrayAdapter<String> ad=new ArrayAdapter<>(chatwithworker.this,android.R.layout.simple_list_item_1,name);
                    l1.setAdapter(ad);

//                    l1.setAdapter(new custom2(chatwithworker.this,name,place));
                    l1.setOnItemClickListener(chatwithworker.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chatwithworker.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ttid=uid.get(i);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("uid", ttid);
        edp.commit();

        Intent ii=new Intent(getApplicationContext(), Test.class);

        ii.putExtra("name",name.get(i));

        startActivity(ii);
    }
}