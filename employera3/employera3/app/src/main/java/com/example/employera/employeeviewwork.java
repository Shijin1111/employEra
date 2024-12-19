package com.example.employera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class employeeviewwork extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String name,url;
    EditText e1;
    Button b1;
    ArrayList<String>work,details,base_amount,wid,time,base_amount1,date;
    ListView LST;
    SharedPreferences sh;
    String url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empviewwork);
        e1=findViewById(R.id.editTextTextPersonName41);
        LST=findViewById(R.id.LST);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1=findViewById(R.id.button27);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(employeeviewwork.this, "hello", Toast.LENGTH_SHORT).show();

                name=e1.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(employeeviewwork.this);
                url = "http://" + sh.getString("ip","") + ":5000/search_work";


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
                            time=new ArrayList<>();
                            date= new ArrayList<>();
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
                                time.add(jo.getString("time"));
                                date.add(jo.getString("date"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                            }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                            LST.setAdapter(new custom3(employeeviewwork.this,work,details,base_amount));
                            LST.setOnItemClickListener(employeeviewwork.this);

                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(employeeviewwork.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("work",name);

                        return params;
                    }
                };


                queue.add(stringRequest);

            }
        });

        RequestQueue queue = Volley.newRequestQueue(employeeviewwork.this);
        url1 = "http://" + sh.getString("ip","") + ":5000/employeeviewworkjobprovider";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
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
                    time=new ArrayList<>();
                    date= new ArrayList<>();

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
                        time.add(jo.getString("time"));
                        date.add(jo.getString("date"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                    LST.setAdapter(new custom3(employeeviewwork.this,work,details,base_amount));
                    LST.setOnItemClickListener(employeeviewwork.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(employeeviewwork.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("name",name);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent in=new Intent(getApplicationContext(),bidbyworker.class);
        in.putExtra("work",details.get(i));
        in.putExtra("amt",base_amount.get(i));
        in.putExtra("jid",wid.get(i));
        in.putExtra("time",time.get(i));
        in.putExtra("date",date.get(i));
//        SharedPreferences.Editor ed=sh.edit();
//        ed.putString("wrk",work.get(i));
//        ed.putString("amt",base_amount.get(i));
//        ed.putString("wid",wid.get(i));
//        ed.commit();
        startActivity(in);

    }
}