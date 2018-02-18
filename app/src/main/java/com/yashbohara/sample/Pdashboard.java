package com.yashbohara.sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.TextAnnotation;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Pdashboard extends AppCompatActivity {
    Bitmap obj;
    byte[] byteArray;
    TextAnnotation text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdashboard);
        TextView scan=(TextView)findViewById(R.id.scan);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        //SCAN STARTED
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });
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
