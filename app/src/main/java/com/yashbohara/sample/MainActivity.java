package com.yashbohara.sample;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.btn_login);
        final ProgressBar p1=(ProgressBar)findViewById(R.id.progressBar);
        TextView reg=(TextView)findViewById(R.id.link_signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //p1.setVisibility(View.VISIBLE);
                Intent i=new Intent(getApplicationContext(),Pdashboard.class);
                startActivity(i);

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
