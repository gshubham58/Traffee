package com.yashbohara.sample;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView admin=(TextView)findViewById(R.id.admin);
        TextView user=(TextView)findViewById(R.id.user);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                intent.putExtra("type","admin");
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                intent.putExtra("type","user");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        exit(0);

    }

}