package com.yashbohara.sample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

public class Reciept extends AppCompatActivity {

sharedpref shr;
    ArrayList<String> item;
    ArrayList<Integer> amount,ids;

    ListView l1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept);
        l1=(ListView)findViewById(R.id.listvw);
        shr=sharedpref.getSharedPref(getApplicationContext());
        RequestQueue queue= Volley.newRequestQueue(Reciept.this);
        String url="https://police-login.herokuapp.com/getdetails/userid="+shr.getvalue("userid").toString();
        Log.e("hh  ",url);
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("response",response);
                    JSONObject obj=new JSONObject(response);
                    JSONArray user=obj.getJSONArray(shr.getvalue("userid").toString());
                    item=new ArrayList<>();
                    amount=new ArrayList<>();
                    ids=new ArrayList<>();
                    int flag=1;
                    for(int i=0;i<user.length();i++)
                    {
                        JSONObject o1=user.getJSONObject(i);

                        if(o1.getString("payment status").equals("completed")) {
                            String finetype = o1.getString("finetype");
                            amount.add(o1.getInt("amount"));
                            flag=0;
//                        Date date= (Date) o1.get("date");
                            int a=o1.getInt("id");
                            item.add(finetype);
                            ids.add(a);
                        }
                        else{


                        }
                    }
                    if(flag==1){
                        Toast.makeText(Reciept.this, "No Receipts available", Toast.LENGTH_SHORT).show();
                    }

                    final ListAdapter listAdapter=new custom_reciept(getApplicationContext(),item,amount);
                    l1.setAdapter(listAdapter);
                    l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Log.e("message",position+"");
                            int cost=amount.get(position);
                            Log.e("amount",cost+""+"bb"+adapterView.getItemAtPosition(position));
                            int id=ids.get(position);
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://police-login.herokuapp.com/getfinepdf/uid="+shr.getvalue("userid").toString()+"&id="+id));
                            startActivity(browserIntent);


                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse (VolleyError error){
                Log.e("error", "volley");
            }
        });
        queue.add(stringRequest);


    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Udashboard.class);
        startActivity(i);
        this.finish();

    }
}
