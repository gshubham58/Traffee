package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Online extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Pdashboard.class);
        startActivity(i);
        this.finish();

    }
}
