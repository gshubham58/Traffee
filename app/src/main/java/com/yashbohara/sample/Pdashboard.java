package com.yashbohara.sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
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
    String mob="",usr="";
   Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdashboard);
        TextView scan=(TextView)findViewById(R.id.scan);
        TextView log=(TextView)findViewById(R.id.p_logs);
        bundle=getIntent().getExtras();
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
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,0);
Log.e("hello","jdjhd");
                RequestQueue queue= Volley.newRequestQueue(Pdashboard.this);
                Log.e("gg","  gghhhh");
                String url="https://vehicleapi.herokuapp.com/num/mp33mb3988";
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
                        }
                    }
                }, new Response.ErrorListener()
//////
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

            }
        });

        //logs
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(),Police_Log_List.class);
               intent.putExtra("policeid",bundle.getString("policeid"));
               startActivity(intent);
            }
        });
        //logs completed
    }
void fun(String temp) {

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
                    Toast.makeText(getApplicationContext(), "Name=" + usr + "\nMobile=" + mob, Toast.LENGTH_LONG).show();
                }

                Intent i=new Intent(getApplicationContext(),FilterFine.class);
                i.putExtra("mobile",mob);
                i.putExtra("username",usr);
                i.putExtra("policeid",bundle.getString("policeid"));
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

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            obj=(Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            obj.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteArray = stream.toByteArray();


            Vision.Builder visionBuilder = new Vision.Builder(
                    new NetHttpTransport(),
                    new AndroidJsonFactory(),
                    null);

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
                        BatchAnnotateImagesResponse batchResponse = vision.images().annotate(batchRequest).execute();
                        Log.e("aa","1") ;
                        text = batchResponse.getResponses()
                                .get(0).getFullTextAnnotation();
                        Log.e("aa","2") ;

//                    Toast.makeText(getApplicationContext(),
//                            text.getText(), Toast.LENGTH_LONG).show();
                        Log.e("result",text.getText());
                        //a(text.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

//                Toast.makeText(getApplicationContext(),
//                            text.getText(), Toast.LENGTH_LONG).show();

        }
    }
        //SCAN COMPLETED


}
