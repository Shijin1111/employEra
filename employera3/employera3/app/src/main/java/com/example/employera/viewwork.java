package com.example.employera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class viewwork extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String url;
    ArrayList<String>work,details,base_amount,wid;
    ListView LST;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewwork);
        LST=findViewById(R.id.LST);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(viewwork.this);
        url = "http://" + sh.getString("ip","") + ":5000/viewworkjobprovider";


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
                        work.add(jo.getString("WORK"));
                        details.add(jo.getString("DETAILS"));
                        base_amount.add(jo.getString("AMOUNT"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                    LST.setAdapter(new custom3(viewwork.this,work,details,base_amount));
                    LST.setOnItemClickListener(viewwork.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewwork.this, "err"+error, Toast.LENGTH_SHORT).show();
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

        Intent in=new Intent(getApplicationContext(),view_bid.class);
        in.putExtra("wid",wid.get(i));
        in.putExtra("work",work.get(i));
        in.putExtra("amt",base_amount.get(i));

        startActivity(in);

    }
}