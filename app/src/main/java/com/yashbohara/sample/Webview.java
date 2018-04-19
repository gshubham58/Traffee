package com.yashbohara.sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Webview extends AppCompatActivity {

int amount;
sharedpref shr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        shr=sharedpref.getSharedPref(getApplicationContext());
        amount=shr.getvalue("fine_amount");
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

                Log.e("url",url);
                if(url.contains("failure")){Intent intent=new Intent(getApplicationContext(),User_Payment_List.class);
                    startActivity(intent);}
                if(url.contains("success")){




                    RequestQueue queue = Volley.newRequestQueue(Webview.this);
                    String ur = "http://http://police-login.herokuapp.com/getdetails/uid=" + shr.getvalue("userid")+"&id="+shr.getvalue("position");
                    Log.e("vvvv",url);
                    final StringRequest stringRequest = new StringRequest(Request.Method.GET, ur, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject o1= null;
                            try {
                                o1 = new JSONObject(response);
                                String st=o1.getString("status");
                                if(st.equals("payment success")){
                                    Toast.makeText(Webview.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", "volley1");
                        }
                    });
                    queue.add(stringRequest);









                    Intent intent=new Intent(getApplicationContext(),Udashboard.class);
                    startActivity(intent);}

            }

        });
        webview.loadUrl("https://pmny.in/FIWLamNTfFih");

    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),User_Payment_List.class);
        startActivity(i);
        this.finish();

    }
}
