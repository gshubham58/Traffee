package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
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

public class FilterFine extends AppCompatActivity {
    TextView name,mobile;
   Button generatefine;
   Spinner finetype;
   Bundle b;
   String type="not having driving liscense";
   int fine;
   String mob,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_fine);
        b=getIntent().getExtras();
        mob=b.getString("mobile");
        username=b.getString("username");
        name=(TextView) findViewById(R.id.filter_Name);
        mobile=(TextView) findViewById(R.id.filter_mobile);
        name.setText(username);
        mobile.setText(mob);
        generatefine=(Button)findViewById(R.id.filter_Button);
        finetype=(Spinner)findViewById(R.id.s1);

        generatefine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=finetype.getSelectedItem().toString();
                //volley request to find fine amount

                RequestQueue queue = Volley.newRequestQueue(FilterFine.this);
                String url = "http://traffic-fines.herokuapp.com/finetype=" + type;
                Log.e("vvvv",url);
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray res = new JSONArray(response);
                            JSONObject obj = res.getJSONObject(0);
                            fine = Integer.parseInt(obj.getString("amount"));
                            Intent i=new Intent(getApplicationContext(),Amount.class);
                            i.putExtra("policeid",b.getString("policeid"));
  //                          Log.e("policeid=",b.getString("policeid"));
                            i.putExtra("userid",username);
//                            Log.e("userid=",b.getString(username));
                            i.putExtra("type",type);
                            i.putExtra("Amount",fine);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "volley1");
                    }
                });
                queue.add(stringRequest);

            }
        });

    }
}
