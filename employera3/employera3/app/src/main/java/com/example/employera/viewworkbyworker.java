package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class viewworkbyworker extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String url;
    ArrayList<String>wid,work,details,base_amount;
    ListView ls3;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewworkbyworker);
        ls3=findViewById(R.id.ls3);
        RequestQueue queue = Volley.newRequestQueue(viewworkbyworker.this);
        url = "http://" + sh.getString("ip","") + ":5000/viewworkbyworker";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {


                    JSONArray ar=new JSONArray(response);
                    wid= new ArrayList<>();
                    work= new ArrayList<>();
                    details= new ArrayList<>();
                    base_amount=new ArrayList<>();
//                            name= new ArrayList<>();
//                            place= new ArrayList<>();
//                            gender=new ArrayList<>();
//                            photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        wid.add(jo.getString("ID"));
                        work.add(jo.getString("work"));
                        details.add(jo.getString("details"));
                        base_amount.add(jo.getString("base_amount"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                    ls3.setAdapter(new custom3(viewworkbyworker.this,work,details,base_amount));
                    ls3.setOnItemClickListener(viewworkbyworker.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewworkbyworker.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}