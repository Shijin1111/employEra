package com.example.employera;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class bidbyworker extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1;
    ArrayList<String>works,base;
    String work,bprice,clbid,bid,url,date,time;
    SharedPreferences sh;

    Date Date1,Date2;
    long millse, mills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidbyworker);
        e1=findViewById(R.id.editTextTextPersonName29);
        e2=findViewById(R.id.editTextTextPersonName30);
        e3=findViewById(R.id.editTextTextPersonName31);
        e4=findViewById(R.id.editTextTextPersonName16);
        b1=findViewById(R.id.button14);



        e1.setText(getIntent().getStringExtra("work"));
        e2.setText(getIntent().getStringExtra("amt"));
       date=getIntent().getStringExtra("date");
       time=getIntent().getStringExtra("time");
        Toast.makeText(bidbyworker.this, "table=---"+time, Toast.LENGTH_SHORT).show();

        try{

            Date Start = null;
            Date End = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss aa");
                    Date systemDate = Calendar.getInstance().getTime();
        String myDate = simpleDateFormat.format(systemDate);
//        String mydd=sdf.format(time);
            try {
                Start = simpleDateFormat.parse(myDate);
                End = simpleDateFormat.parse(time);}
            catch(ParseException e){
                //Some thing if its not working
            }
            long difference = End.getTime() - Start.getTime();
             mills = Math.abs(difference);
            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            long Secs = (int) (mills / 1000) % 60;
            if(hours < 0){
                hours+=24;
            }if(min < 0){
                float  newone = (float)min/60 ;
                min +=60;
                hours =(int) (hours +newone);}
            String c = hours+":"+min+":"+Secs;
            Log.d("ANSWER",c);
                        Toast.makeText(bidbyworker.this, "diff"+c, Toast.LENGTH_SHORT).show();
        }
                catch (Exception e)
    {

    }



        e1.setEnabled(false);
        e2.setEnabled(false);



//        static long durationToMillis(String strDuration) {
//            String[] arr = strDuration.split(":");
//            Duration duration = Duration.ZERO;
//            if (arr.length == 2) {
//                strDuration = "PT" + arr[0] + "M" + arr[1] + "S";
//                duration = Duration.parse(strDuration);
//            }
//            return duration.toMillis();
//        }
//        CountDownTimer myCountDown=new CountDownTimer(30000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                t1.setText("seconds remaining: " + millisUntilFinished / 1000);
//                // logic to set the EditText could go here
//            }
//            public void onFinish() {
//                t1.setText("Finished!");
//                Intent in=new Intent(getApplicationContext(),workerhome.class);
//                startActivity(in);
//            }
//
//        };

//        CountDownTimer myCountDown = new CountDownTimer(3600000, 1) {
//            public void onTick(long millisUntilFinished) {
//                //update the UI with the new count
//
//
//
//                t1.setText(String.valueOf(millisUntilFinished));
//
//            }
//
//            public void onFinish() {
//                //start the activity
//            }
//        };
//start the countDown
//        myCountDown.start();

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(bidbyworker.this);
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

                    if(res.equalsIgnoreCase("")){
                        e3.setText(0);
                        e3.setEnabled(false);
                    }
                    else{
                        e3.setText(res);
                        e3.setEnabled(false);
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
                params.put("jobid", getIntent().getStringExtra("jid"));


                return params;
            }
        };
        queue.add(stringRequest);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bamt = e4.getText().toString();
                sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                RequestQueue queue = Volley.newRequestQueue(bidbyworker.this);
                url = "http://" + sh.getString("ip","") + ":5000/add_bidamount";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("data");

                            if (res.equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                Intent ik = new Intent(getApplicationContext(), workerhome.class);
                                startActivity(ik);

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
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
                        params.put("jobid", getIntent().getStringExtra("jid"));
                        params.put("lid", sh.getString("lid",""));
                        params.put("bamt",bamt);



                        return params;
                    }
                };
                queue.add(stringRequest);


            }
        });


    }
}
