package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OfflineChallan extends AppCompatActivity {
TextView offlogo,offstatus;
sharedpref shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_challan);
        shr = sharedpref.getSharedPref(getApplicationContext());
        offlogo=(TextView)findViewById(R.id.offstatus);
        offstatus=(TextView)findViewById(R.id.offstatuscode);

        //offlogo.setText(shr.getvalue("status").toString());
        offstatus.setText("Completed");
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Pdashboard.class);
        startActivity(i);
        this.finish();

    }
}
