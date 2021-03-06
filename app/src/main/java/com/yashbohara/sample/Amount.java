package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Amount extends AppCompatActivity {
TextView fineamount;
int fine;
ProgressBar progressBar;
Button off,on;
Date date;
sharedpref shr;
Calendar calendar;
SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);

        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        shr=sharedpref.getSharedPref(getApplicationContext());
        off=(Button)findViewById(R.id.offline);
        on=(Button)findViewById(R.id.online);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        fine=shr.getvalue("Amount");
        fineamount=(TextView)findViewById(R.id.amountValue);
        fineamount.setText(fine+" Rs");
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressBar.setVisibility(View.VISIBLE);
                //save challan in the database
                RequestQueue queue= Volley.newRequestQueue(Amount.this);
                String url="https://police-login.herokuapp.com/addpolice/policeid="+shr.getvalue("policeid").toString()+"&userid="+shr.getvalue("fine_user").toString()+"&finetype="+shr.getvalue("type").toString()+"&amount="+fine+"&date="+simpleDateFormat.format(calendar.getTime())+"&mode=offline";
                Log.e("hh  ",url);
                final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONArray res=new JSONArray(response);
                            JSONObject obj=new JSONObject(response);
                            String result=obj.getString("status");
                            if(result.equals("success"))
                            {
                                //progressBar.setVisibility(View.INVISIBLE);
                                Intent intent=new Intent(getApplicationContext(),OfflineChallan.class);
                                intent.putExtra("status","f00c");
                                shr.setvalue("status","f00c");
                                intent.putExtra("statuscode","Completed");
                                startActivity(intent);
                            }
                            else if(!result.equals("success"))
                            {
                                Toast.makeText(getApplicationContext(),"Challan not generated due to some network error",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse (VolleyError error){
                        Log.e("error0000", error+"");
                    }
                });
                queue.add(stringRequest);
                //challan save

            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Save online chalan

//                progressBar.setVisibility(View.VISIBLE);
                //save challan in the database
                RequestQueue queue= Volley.newRequestQueue(Amount.this);
                String type=shr.getvalue("type").toString();
                String url="https://police-login.herokuapp.com/addpolice/policeid="+shr.getvalue("policeid").toString()+"&userid="+shr.getvalue("fine_user").toString()+"&finetype="+shr.getvalue("type").toString()+"&amount="+fine+"&date="+simpleDateFormat.format(calendar.getTime())+"&mode=online";
                Log.e("hh  ",url);
                final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONArray res=new JSONArray(response);
                            JSONObject obj=new JSONObject(response);
                            String result=obj.getString("status");
                            if(result.equals("success"))
                            {



//                                RequestQueue queue= Volley.newRequestQueue(Amount.this);
//                                String url="https://police-login.herokuapp.com/addpolice/policeid="+shr.getvalue("policed").toString();
//                                Log.e("hh  ",url);
//                                final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
//                                        try {
//                                            //JSONArray res=new JSONArray(response);
//                                            JSONObject obj=new JSONObject(response);
//                                            //parse json to get latitude and longitude
//
//                                            //json parsed
//                                            //send message
//
//                                            //message sent
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }, new Response.ErrorListener()
//
//                                {
//                                    @Override
//                                    public void onErrorResponse (VolleyError error){
//                                        Log.e("error0000", error+"");
//                                    }
//                                });
//                                queue.add(stringRequest);
                                //challan save





                                //progressBar.setVisibility(View.INVISIBLE);
                                Intent intent=new Intent(getApplicationContext(),OfflineChallan.class);
                                intent.putExtra("status","&#xf00c");
                                shr.setvalue("status","&#xf00c");
                                intent.putExtra("statuscode","Completed");
                                startActivity(intent);
                            }
                            else if(!result.equals("success"))
                            {
                                Toast.makeText(getApplicationContext(),"Challan not generated due to some network error",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse (VolleyError error){
                        Log.e("error1111", error+"");
                    }
                });
                queue.add(stringRequest);
                //challan saved
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),FilterFine.class);
        startActivity(i);
        this.finish();

    }
}
