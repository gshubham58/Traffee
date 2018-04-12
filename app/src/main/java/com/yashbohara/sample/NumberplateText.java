package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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

public class NumberplateText extends AppCompatActivity {
EditText edtxt;
Button btn;
ProgressBar p1;
sharedpref shr;
String mob="",usr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberplate_text);
        shr = sharedpref.getSharedPref(getApplicationContext());
        btn=(Button)findViewById(R.id.search);
        edtxt=(EditText)findViewById(R.id.edttxt);
        p1=(ProgressBar)findViewById(R.id.progressBar);

        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String registr=edtxt.getText().toString();
            p1.setVisibility(View.VISIBLE);
//                Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,0);
            Log.e("hello","jdjhd");
            RequestQueue queue= Volley.newRequestQueue(NumberplateText.this);
            Log.e("gg","  gghhhh");
            String url="https://vehicleapi.herokuapp.com/num/"+registr;
            final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj=new JSONObject(response);
                        String result=obj.getString("engine no");
                        Log.e("res  ",result);
                        fun(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        p1.setVisibility(View.INVISIBLE);
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
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);

        }
    });
    }
    void fun(String temp) {

        Log.e("yooo","1");
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://login-api-demo.herokuapp.com/getdetails/engno=" + temp;
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("yoooo","2");
                    JSONArray res = new JSONArray(response);
                    if(res.length()>0) {
                        Log.e("yoooo", "3");
                        JSONObject obj = res.getJSONObject(0);

                        Log.e("yoooo", "4");
                        mob = obj.getString("mobile");
                        shr.setvalue("fine_mobile",mob);


                        Log.e("yoooo", "5");
                        usr = obj.getString("username");
                        shr.setvalue("fine_user",usr);
                        Log.e("yooo", mob);

                    }

                    Intent i=new Intent(getApplicationContext(),FilterFine.class);
                    p1.setVisibility(View.INVISIBLE);
                    startActivity(i);
                } catch (JSONException e) {
                    Log.e("bbbbbbb","nnnn");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error+"");
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
