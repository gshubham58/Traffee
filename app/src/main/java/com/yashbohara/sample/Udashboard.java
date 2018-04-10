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

import com.android.volley.DefaultRetryPolicy;
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
    ProgressBar p1;
    sharedpref shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udashboard);
        pay=(TextView)findViewById(R.id.i2);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        logout=(TextView)findViewById(R.id.i4);
        shr=sharedpref.getSharedPref(getApplicationContext());
        pendingfee=(TextView)findViewById(R.id.Pendingfee);
//    pay.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent i=new Intent(Udashboard.this,Payment.class);
//            startActivity(i);
//        }
//    });
//    logout.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            FirebaseAuth.getInstance().signOut();
//        }
//    });

    //pending fee
//        pendingfee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p1.setVisibility(View.VISIBLE);
//            Intent intent=new Intent(getApplicationContext(),User_Payment_List.class);
//            intent.putExtra("userid",bundle.getString("userid"));
//                p1.setVisibility(View.INVISIBLE);
//                startActivity(intent);
//            }
//        });
    }
    public void logout_clicked(View view){
        FirebaseAuth.getInstance().signOut();
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            sharedpref shr=sharedpref.getSharedPref(getApplicationContext());
            shr.delete("userid");
            startActivity(i);
            this.finish();


    }
    public void pendingfee_clicked(View view){
        p1.setVisibility(View.VISIBLE);
        Intent intent=new Intent(getApplicationContext(),User_Payment_List.class);

        p1.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }
    public void profile_user(View view){
        Intent intent=new Intent(Udashboard.this,Userdetails.class);
        startActivity(intent);
    }
    public void reciept_clicked(View view){
        Intent i=new Intent(getApplicationContext(),Reciept.class);

        startActivity(i);













    }
}
