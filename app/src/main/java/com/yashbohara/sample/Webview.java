package com.yashbohara.sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {
Bundle bundle;
int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        bundle=getIntent().getExtras();
        amount=bundle.getInt("amount");
        Log.e("amount",amount+"");
       /* AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
        builder1.setMessage("Amount to be paid "+amount+" Rs");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
       */
        AlertDialog.Builder alertbox=new AlertDialog.Builder(Webview.this,R.style.MyDialogTheme);


        alertbox.setTitle("Amount To be Paid");
        alertbox.setMessage(""+amount);
        alertbox.setCancelable(true);
        alertbox.setPositiveButton("Yes", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.cancel();
                        /////operations
                    }
                });

        alertbox.show();
       WebView webview=(WebView) findViewById(R.id.web);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if(url.contains("failure")){Intent intent=new Intent(getApplicationContext(),Payment.class);
                    startActivity(intent);}

            }

        });
        webview.loadUrl("https://pmny.in/FIWLamNTfFih");

    }
}
