package com.example.employera;

import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class view_bid extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText e1,e2,e3;

    ArrayList<String>bidder,rating,bidamount,ID;
    ListView ls4;


    String url;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bid);
        e1=findViewById(R.id.editTextTextPersonName29);
        e2=findViewById(R.id.editTextTextPersonName30);
        e3=findViewById(R.id.editTextTextPersonName31);

        ls4 = findViewById(R.id.ls4);


        e1.setText(getIntent().getStringExtra("work"));
        e2.setText(getIntent().getStringExtra("amt"));

        e1.setEnabled(false);
        e2.setEnabled(false);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(view_bid.this);
        url = "http://" + sh.getString("ip","") + ":5000/view_lowestbid";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    JSONObject json = new JSONObject(response);
                    String res = json.getString("data");

                    e3.setText(res);
                    e3.setEnabled(false);


                } catch (JSONException e) {
                    Toast.makeText(view_bid.this, ""+e, Toast.LENGTH_SHORT).show();
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
                params.put("jobid", getIntent().getStringExtra("wid"));


                return params;
            }
        };
        queue.add(stringRequest);



        RequestQueue queue1 = Volley.newRequestQueue(view_bid.this);
        url = "http://" + sh.getString("ip","") + ":5000/viewbidresult";
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
                // Display the response string.
                Log.d("+++++++++++++++++",response1);
                try {

//                    Toast.makeText(view_bid.this, ""+response1, Toast.LENGTH_SHORT).show();

                    JSONArray ar=new JSONArray(response1);
                    bidder= new ArrayList<>();

                    rating= new ArrayList<>();
                    bidamount=new ArrayList<>();
                    ID=new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        bidder.add(jo.getString("FIRSTNAME")+jo.getString("LASTNAME"));
                        rating.add(jo.getString("avgr"));
                        bidamount.add(jo.getString("bidamount"));
                        ID.add(jo.getString("b_id"));
//

                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                    ls4.setAdapter(new custom3(view_bid.this,bidder,rating,bidamount));
                    ls4.setOnItemClickListener(view_bid.this);

                } catch (Exception e) {
//                    Toast.makeText(view_bid.this, ""+e, Toast.LENGTH_SHORT).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_bid.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("jobid", getIntent().getStringExtra("wid"));

                return params;
            }
        };
        queue1.add(stringRequest1);

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder ald=new AlertDialog.Builder(view_bid.this);
        ald.setTitle("File")
                .setPositiveButton("Accept ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RequestQueue queue = Volley.newRequestQueue(view_bid.this);
                        url = "http://" + sh.getString("ip","") + ":5000/bidaccept";

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response string.
                                Log.d("+++++++++++++++++", response);
                                try {
                                    JSONObject json = new JSONObject(response);
                                    String res = json.getString("task");

                                    if (res.equalsIgnoreCase("success")) {
                                        Toast.makeText(view_bid.this, "worker's request has been accepted", Toast.LENGTH_SHORT).show();
                                        Intent ik = new Intent(getApplicationContext(), viewwork.class);
                                        startActivity(ik);

                                    } else {

                                        Toast.makeText(view_bid.this, "Error", Toast.LENGTH_SHORT).show();

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
                                params.put("ID",ID.get(i));
                                return params;
                            }
                        };
                        queue.add(stringRequest);

                    }
                })
                .setNegativeButton("Reject", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RequestQueue queue = Volley.newRequestQueue(view_bid.this);
                        url = "http://" + sh.getString("ip","") + ":5000/bidreject";

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response string.
                                Log.d("+++++++++++++++++", response);
                                try {
                                    JSONObject json = new JSONObject(response);
                                    String res = json.getString("task");

                                    if (res.equalsIgnoreCase("rejected")) {
                                        Toast.makeText(view_bid.this, "worker's request has been rejected", Toast.LENGTH_SHORT).show();
                                        Intent ik = new Intent(getApplicationContext(), viewwork.class);
                                        startActivity(ik);

                                    } else {

                                        Toast.makeText(view_bid.this, "Error", Toast.LENGTH_SHORT).show();

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
                                params.put("ID",ID.get(i));
                                return params;
                            }
                        };
                        queue.add(stringRequest);


                    }
                });

        AlertDialog al=ald.create();
        al.show();

    }
}
