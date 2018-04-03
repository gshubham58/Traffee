package com.yashbohara.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Userdetails extends AppCompatActivity {
TextView user_id,user_mobile;
Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
    bundle=getIntent().getExtras();
        user_id=(TextView)findViewById(R.id.user_id);
    user_mobile=(TextView)findViewById(R.id.user_mobile);
    user_mobile.setText(" Mobile - "+bundle.getString("mobile"));
    user_id.setText(" UserId - "+bundle.getString("userid"));

    }
}
