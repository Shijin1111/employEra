package com.example.employera;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
public class viewbidresult extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String url,lowest;
    ArrayList<String>bidder,rating,bidamount,ID;
    ListView ls4;
    EditText e1,e2,e3;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbidresult);
        ls4=findViewById(R.id.ls4);
        e1=findViewById(R.id.editTextTextPersonName14);
        e2=findViewById(R.id.editTextTextPersonName18);
        e3=findViewById(R.id.editTextTextPersonName19);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1.setText(sh.getString("wrk",""));
        e2.setText(sh.getString("amt",""));
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e3.setTextColor(ContextCompat.getColor(this, R.color.black));

        e1.setTextColor(ContextCompat.getColor(this, R.color.black));
        e2.setTextColor(ContextCompat.getColor(this, R.color.black));
        viewbidamt();


        RequestQueue queue = Volley.newRequestQueue(viewbidresult.this);
        url = "http://" + sh.getString("ip","") + ":5000/viewbidresult";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {


                    JSONArray ar=new JSONArray(response);
                    bidder= new ArrayList<>();

                    rating= new ArrayList<>();
                    bidamount=new ArrayList<>();
                    ID=new ArrayList<>();
//                            name= new ArrayList<>();
//                            place= new ArrayList<>();
//                            gender=new ArrayList<>();
//                            photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        bidder.add(jo.getString("FIRSTNAME")+jo.getString("LASTNAME"));
                        rating.add(jo.getString("avgr"));
                        bidamount.add(jo.getString("bidamount"));
                        ID.add(jo.getString("ID"));
//                                name.add(jo.getString("name"));
//                                place.add(jo.getString("place"));
//                                gender.add(jo.getString("gender"));
//                                photo.add(jo.getString("photo"));


                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(reportuser.this,android.R.layout.simple_list_item_1,skills);
//                    l1.setAdapter(ad);

                    ls4.setAdapter(new custom3(viewbidresult.this,bidder,rating,bidamount));
                    ls4.setOnItemClickListener(viewbidresult.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewbidresult.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("jid",getIntent().getStringExtra("wid"));

                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void viewbidamt() {
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(viewbidresult.this);
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
                params.put("jobid", getIntent().getStringExtra("jid"));


                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder ald=new AlertDialog.Builder(viewbidresult.this);
        ald.setTitle("File")
                .setPositiveButton("Accept ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RequestQueue queue = Volley.newRequestQueue(viewbidresult.this);
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
                                        Toast.makeText(viewbidresult.this, "worker's request has been accepted", Toast.LENGTH_SHORT).show();
                                        Intent ik = new Intent(getApplicationContext(), viewbidresult.class);
                                        startActivity(ik);

                                    } else {

                                        Toast.makeText(viewbidresult.this, "Error", Toast.LENGTH_SHORT).show();

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
                        RequestQueue queue = Volley.newRequestQueue(viewbidresult.this);
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
                                        Toast.makeText(viewbidresult.this, "worker's request has been rejected", Toast.LENGTH_SHORT).show();
                                        Intent ik = new Intent(getApplicationContext(), viewbidresult.class);
                                        startActivity(ik);

                                    } else {

                                        Toast.makeText(viewbidresult.this, "Error", Toast.LENGTH_SHORT).show();

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