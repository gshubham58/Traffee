package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Policedetails extends AppCompatActivity {
TextView policeidtxt,policemobiletxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policedetails);

policeidtxt=(TextView)findViewById(R.id.detailspoliceid);
policemobiletxt=(TextView)findViewById(R.id.detailsmobile);
sharedpref s=sharedpref.getSharedPref(getApplicationContext());

policeidtxt.setText(" PoliceId - "+s.getvalue("policeid"));
policemobiletxt.setText(" Mobile - "+s.getvalue("police_mobile"));
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Pdashboard.class);
        startActivity(i);
        this.finish();

    }
}
