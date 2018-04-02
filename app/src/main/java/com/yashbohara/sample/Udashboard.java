package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Udashboard extends AppCompatActivity {
TextView pay,logout,pendingfee;
ProgressBar progressBar;
Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udashboard);
        pay=(TextView)findViewById(R.id.i2);
        logout=(TextView)findViewById(R.id.i4);
        pendingfee=(TextView)findViewById(R.id.Pendingfee);
        bundle=getIntent().getExtras();
//    pay.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent i=new Intent(Udashboard.this,Payment.class);
//            startActivity(i);
//        }
//    });
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
        }
    });

    //pending fee
        pendingfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),User_Payment_List.class);
            intent.putExtra("userid",bundle.getString("userid"));
            startActivity(intent);
            }
        });
    }

}
