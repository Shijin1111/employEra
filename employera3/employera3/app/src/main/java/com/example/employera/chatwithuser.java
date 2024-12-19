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

public class chatwithuser extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences sh;
    ListView l1;
    String url,ttid;
    ArrayList<String> user,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwithuser);
        l1=findViewById(R.id.LIST4);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = "http://" + sh.getString("ip", "") + ":5000/chatwithworker";
        RequestQueue queue = Volley.newRequestQueue(chatwithuser.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(chatbot.this, response, Toast.LENGTH_LONG).show();

                    JSONArray ar = new JSONArray(response);
//                    user = new ArrayList<>();
                    user = new ArrayList<>();
//                    place=new ArrayList<>();
                    uid=new ArrayList<>();



                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        user.add(jo.getString("FIRSTNAME") + jo.getString("LASTNAME"));
                        uid.add(jo.getString("LID"));

                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(chatwithuser.this,android.R.layout.simple_list_item_1,user);
                    l1.setAdapter(ad);

//                    l1.setAdapter(new custom6(chatbot.this, date, details ,image));
                    l1.setOnItemClickListener(chatwithuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chatwithuser.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ttid=uid.get(position);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("uid", ttid);
        edp.commit();

        Intent i=new Intent(getApplicationContext(), Test.class);

        i.putExtra("name",user.get(position));

        startActivity(i);


    }
}
