package com.yashbohara.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Policedetails extends AppCompatActivity {
TextView policeidtxt,policemobiletxt;
Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policedetails);
        bundle=getIntent().getExtras();
policeidtxt=(TextView)findViewById(R.id.detailspoliceid);
policemobiletxt=(TextView)findViewById(R.id.detailsmobile);
policeidtxt.setText(" PoliceId - "+bundle.getString("policeid"));
policemobiletxt.setText(" Mobile - "+bundle.getString("mobile"));
    }
}
