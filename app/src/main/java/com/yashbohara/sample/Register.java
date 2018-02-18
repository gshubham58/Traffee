package com.yashbohara.sample;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity {
    EditText password,mobile,engineno,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button b1=(Button) findViewById(R.id.btn_reg);
        userid=(EditText) findViewById(R.id.name);
        engineno=(EditText) findViewById(R.id.EngineNumber);
        mobile=(EditText) findViewById(R.id.regmobile);
        password=(EditText) findViewById(R.id.reg_password);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userid.length()==0)
                {
                    userid.requestFocus();
                    userid.setError("FIELD CANNOT BE EMPTY");
                }

                String a,b,c,d,e;
                a=userid.getText().toString();
                b=password.getText().toString();
                c= mobile.getText().toString();
                d=engineno.getText().toString();

                Log.e("value  ",a+b+c+d);
                RequestQueue queue= Volley.newRequestQueue(Register.this);
                String url="https://login-api-demo.herokuapp.com/adduser/userid="+a+"&pwd="+b+"&mob="+c+"&engno="+d+"&type=user";
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
                                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                            else if(!result.equals("success"))
                            {
                                Toast.makeText(getApplicationContext(),"User name already available please choose another name",Toast.LENGTH_LONG).show();

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
        });
    }
}
