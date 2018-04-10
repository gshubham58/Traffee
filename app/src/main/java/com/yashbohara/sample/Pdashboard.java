package com.yashbohara.sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.TextAnnotation;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Pdashboard extends AppCompatActivity {
    Bitmap obj;
    byte[] byteArray;
    TextAnnotation text;
    String mob="",usr="",numberplate;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdashboard);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        TextView scan=(TextView)findViewById(R.id.scan);
        TextView log=(TextView)findViewById(R.id.p_logs);

//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//

        //SCAN STARTED
//        scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p1.setVisibility(View.VISIBLE);
////                Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
////                startActivityForResult(intent,0);
//Log.e("hello","jdjhd");
//                RequestQueue queue= Volley.newRequestQueue(Pdashboard.this);
//                Log.e("gg","  gghhhh");
//                String url="https://vehicleapi.herokuapp.com/num/"+numberplate;
//                final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj=new JSONObject(response);
//                            String result=obj.getString("engine no");
//                            Log.e("res  ",result);
//                            fun(result);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            p1.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                }, new Response.ErrorListener()
//
//                {
//                    @Override
//                    public void onErrorResponse (VolleyError error){
//                        Log.e("error", ""+error);
//                    }
//                });
//                stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                queue.add(stringRequest);
//
//            }
//        });

        //logs
//        log.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p1.setVisibility(View.VISIBLE);
//               Intent intent=new Intent(getApplicationContext(),Police_Log_List.class);
//               intent.putExtra("policeid",bundle.getString("policeid"));
//                p1.setVisibility(View.INVISIBLE);
//                startActivity(intent);
//            }
//        });
        //logs completed
    }
    public void details_clicked(View view){

        Intent intent=new Intent(Pdashboard.this,Policedetails.class);
        startActivity(intent);


    }
    public void logs_clicked(View view)
    {
        p1.setVisibility(View.VISIBLE);
        Intent intent=new Intent(getApplicationContext(),Police_Log_List.class);

        p1.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }
    public void scan_clicked(View view) {
       /* p1.setVisibility(View.VISIBLE);
//                Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,0);
        Log.e("hello","jdjhd");
        RequestQueue queue= Volley.newRequestQueue(Pdashboard.this);
        Log.e("gg","  gghhhh");
        String url="https://vehicleapi.herokuapp.com/num/"+numberplate;
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    String result=obj.getString("engine no");
                    Log.e("res  ",result);
                    fun(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                    p1.setVisibility(View.INVISIBLE);
                }
            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Log.e("error", ""+error);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
*/
       Intent i=new Intent(getApplicationContext(),NumberplateText.class);
        startActivity(i);
    }
    public void logout_clicked(View view){
        sharedpref shr=sharedpref.getSharedPref(getApplicationContext());
        shr.delete("userid");
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            this.finish();


    }
     /*   void fun(String temp) {

    Log.e("yooo","1");
    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    String url = "https://login-api-demo.herokuapp.com/getdetails/engno=" + temp;
    final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                Log.e("yoooo","2");
                JSONArray res = new JSONArray(response);
                if(res.length()>0) {
                    Log.e("yoooo", "3");
                    JSONObject obj = res.getJSONObject(0);

                    Log.e("yoooo", "4");
                    mob = obj.getString("mobile");
                    Log.e("yoooo", "5");
                    usr = obj.getString("username");
                    Log.e("yooo", mob);

                }

                Intent i=new Intent(getApplicationContext(),FilterFine.class);
                i.putExtra("mobile",mob);
                i.putExtra("username",usr);
                i.putExtra("policeid",bundle.getString("policeid"));
                p1.setVisibility(View.INVISIBLE);
                startActivity(i);
            } catch (JSONException e) {
                Log.e("bbbbbbb","nnnn");
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("error", error+"");
        }
    });
    stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    queue.add(stringRequest);

}*/
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            Log.e("1","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            obj=(Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            obj.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();


            Vision.Builder visionBuilder = new Vision.Builder(
                    new NetHttpTransport(),
                    new AndroidJsonFactory(),
                    null);
            Log.e("2","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            visionBuilder.setVisionRequestInitializer(
                    new VisionRequestInitializer("AIzaSyB51FD06nqSckijl2iKPLiaBb0SSrPGC7o"));
            final Vision vision = visionBuilder.build();

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    com.google.api.services.vision.v1.model.Image inputImage = new com.google.api.services.vision.v1.model.Image();
                    inputImage.encodeContent(byteArray);


                    Feature desiredFeature = new Feature();
                    desiredFeature.setType("TEXT_DETECTION");
                    AnnotateImageRequest request = new AnnotateImageRequest();
                    request.setImage(inputImage);
                    request.setFeatures(Arrays.asList(desiredFeature));
                    BatchAnnotateImagesRequest batchRequest =
                            new BatchAnnotateImagesRequest();

                    batchRequest.setRequests(Arrays.asList(request));


                    try {
                        Log.e("3","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
                        BatchAnnotateImagesResponse batchResponse = vision.images().annotate(batchRequest).execute();
                        Log.e("aa","1") ;
                        text = batchResponse.getResponses()
                                .get(0).getFullTextAnnotation();
                        Log.e("aa","2") ;
numberplate=text.getText().toString();
//                    Toast.makeText(getApplicationContext(),
//                            text.getText(), Toast.LENGTH_LONG).show();
                        Log.e("result",text.getText());
                        //a(text.getText().toString());
                    } catch (IOException e) {
                        Log.e("4","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
                        e.printStackTrace();
                        Log.e("5","jjjjjjjjjjjjj");
                    }

                }
            });

//                Toast.makeText(getApplicationContext(),
//                            text.getText(), Toast.LENGTH_LONG).show();

        }
    }
        //SCAN COMPLETED
*/

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        this.finish();

    }
}
