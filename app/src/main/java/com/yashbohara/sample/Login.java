package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
public class Login extends AppCompatActivity {
    EditText name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button b1=(Button)findViewById(R.id.btn_login);
        final ProgressBar p1=(ProgressBar)findViewById(R.id.progressBar);
        TextView reg=(TextView)findViewById(R.id.link_signup);
        Bundle bundle=getIntent().getExtras();
        //final String type=bundle.getString("type");
        name=(EditText)findViewById(R.id.MobileNumber);
        pass=(EditText)findViewById(R.id.input_password);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //p1.setVisibility(View.VISIBLE);
               // Log.e("type",type);
//                Intent i=new Intent(getApplicationContext(),Pdashboard.class);
//                startActivity(i);}
                if(name.length()==0)
                {
                    name.requestFocus();
                    name.setError("FIELD CANNOT BE EMPTY");
                }
                if(pass.length()==0)
                {
                    pass.requestFocus();
                    pass.setError("FIELD CANNOT BE EMPTY");
                }
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    String url="https://login-api-demo.herokuapp.com/getdetails/user="+name.getText().toString();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        Log.e("onresponse     ",response);
                                        JSONArray ar=new JSONArray(response);
                                       if(ar.length()==0){
                                           Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_LONG).show();
                                       }
                                       else {
                                           JSONObject ob = ar.getJSONObject(0);
                                           String us = ob.getString("username");
                                           String ps = ob.getString("password");
                                           String type=ob.getString("type");
                                           String enginenumber=ob.getString("engineno");
                                           String mobile=ob.getString("mobile");
                                           if (ps.equals(pass.getText().toString())) {
                                               if(type.equals("admin")){
                                               Intent intent = new Intent(getApplicationContext(), Pdashboard.class);
                                               startActivity(intent);}
                                               else if(type.equals("user")){
                                                   Intent intent = new Intent(getApplicationContext(), Udashboard.class);
                                                   startActivity(intent);
                                               }
                                           } else {
                                               Toast.makeText(Login.this, " Invalid Password", Toast.LENGTH_LONG).show();
                                           }
                                       }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", "volley");
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);



            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
    }






}
