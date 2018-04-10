package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

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

public class Police_Log_List extends AppCompatActivity {

    ArrayList<Integer> amount;
    ArrayList<String> list;
ListView l1;
    sharedpref shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police__log__list);

        l1=(ListView)findViewById(R.id.police_list);
        RequestQueue queue= Volley.newRequestQueue(Police_Log_List.this);
        shr=sharedpref.getSharedPref(getApplicationContext());
        String url="https://police-login.herokuapp.com/getdetails/policeid="+shr.getvalue("policeid");
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    JSONArray user=obj.getJSONArray(shr.getvalue("policeid").toString());
                    list=new ArrayList<>();
                    amount=new ArrayList<>();
                    for(int i=0;i<user.length();i++)
                    {
                        JSONObject o1=user.getJSONObject(i);
                        String finetype=o1.getString("finetype");
                        amount.add(o1.getInt("amount"));
//                        Date date= (Date) o1.get("date");
                        list.add(finetype);
                        final ListAdapter listAdapter=new police_customAdapter(getApplicationContext(),list,amount);
                        l1.setAdapter(listAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
//////
        {
            @Override
            public void onErrorResponse (VolleyError error){
                Log.e("error", ""+error);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Pdashboard.class);
        startActivity(i);
        this.finish();

    }
}
