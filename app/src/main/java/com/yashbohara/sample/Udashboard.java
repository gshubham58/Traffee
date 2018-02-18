package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Udashboard extends AppCompatActivity {
TextView pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udashboard);
        pay=(TextView)findViewById(R.id.i2);
    pay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(Udashboard.this,Payment.class);
            startActivity(i);
        }
    });
    }

}
