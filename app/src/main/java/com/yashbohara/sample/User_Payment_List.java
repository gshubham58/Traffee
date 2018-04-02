package com.yashbohara.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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
import java.util.Date;

public class User_Payment_List extends AppCompatActivity {
ProgressBar progressBar;
Bundle bundle;
ArrayList<String> item;
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__payment__list);
        l1=(ListView) findViewById(R.id.list1);
        bundle=getIntent().getExtras();
        //progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue= Volley.newRequestQueue(User_Payment_List.this);
        String url="https://police-login.herokuapp.com/getdetails/userid="+bundle.getString("userid");
        Log.e("hh  ",url);
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    JSONArray user=obj.getJSONArray(bundle.getString("userid"));
                    item=new ArrayList<>();
                    for(int i=0;i<user.length();i++)
                    {
                        JSONObject o1=user.getJSONObject(i);
                        String finetype=o1.getString("finetype");
                        int amount=o1.getInt("amount");
//                        Date date= (Date) o1.get("date");
                        item.add(finetype);
                        final ListAdapter listAdapter=new CustomAdapter(getApplicationContext(),item);
                        l1.setAdapter(listAdapter);
                    }
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
}