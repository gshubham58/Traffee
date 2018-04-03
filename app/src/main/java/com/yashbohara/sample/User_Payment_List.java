package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
Button pay;
ArrayList<String> item;
ArrayList<Integer> amount;
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
                    Log.e("response",response);
                    JSONObject obj=new JSONObject(response);
                    JSONArray user=obj.getJSONArray(bundle.getString("userid"));
                    item=new ArrayList<>();
                    amount=new ArrayList<>();
                    for(int i=0;i<user.length();i++)
                    {
                        JSONObject o1=user.getJSONObject(i);
                        String finetype=o1.getString("finetype");
                        amount.add(o1.getInt("amount"));
//                        Date date= (Date) o1.get("date");
                        item.add(finetype);

                    }

                    final ListAdapter listAdapter=new CustomAdapter(getApplicationContext(),item,amount);
                    l1.setAdapter(listAdapter);
//                    l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                            Log.e("message",position+"");
//                            int cost=amount.get(position);
//                            Log.e("amount",cost+""+"bb"+adapterView.getItemAtPosition(position));
//
//                            Intent intent=new Intent(User_Payment_List.this,Webview.class);
//                            intent.putExtra("amount",cost);
//                            startActivity(intent);
//                        }
//                    });


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
    public void list_clicked(View view)
    {
        Log.e("list","clicked");
        Intent intent=new Intent(User_Payment_List.this,Webview.class);
        startActivity(intent);
    }
}