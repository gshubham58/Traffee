package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Userdetails extends AppCompatActivity {
TextView user_id,user_mobile;
sharedpref shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
shr=sharedpref.getSharedPref(getApplicationContext());
        user_id=(TextView)findViewById(R.id.user_id);
    user_mobile=(TextView)findViewById(R.id.user_mobile);
    user_mobile.setText(" Mobile - "+shr.getvalue("user_mobile").toString());
    user_id.setText(" UserId - "+shr.getvalue("userid").toString());

    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Udashboard.class);
        startActivity(i);
        this.finish();

    }
}
