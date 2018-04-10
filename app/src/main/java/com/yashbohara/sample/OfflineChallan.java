package com.yashbohara.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OfflineChallan extends AppCompatActivity {
TextView offlogo,offstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_challan);
        offlogo=(TextView)findViewById(R.id.offstatus);
        offstatus=(TextView)findViewById(R.id.offstatuscode);

       // offlogo.setText(bundle.getString("status"));
        offstatus.setText("Completed");
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Pdashboard.class);
        startActivity(i);
        this.finish();

    }
}
