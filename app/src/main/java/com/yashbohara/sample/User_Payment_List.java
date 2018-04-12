package com.yashbohara.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class User_Payment_List extends AppCompatActivity {
ProgressBar progressBar;

ArrayList<String> item;
ArrayList<Integer> amount;
    ListView l1;
sharedpref shr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__payment__list);
        l1=(ListView) findViewById(R.id.list1);
        shr=sharedpref.getSharedPref(getApplicationContext());
        //progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue= Volley.newRequestQueue(User_Payment_List.this);
        String url="https://police-login.herokuapp.com/getdetails/userid="+shr.getvalue("userid").toString();
        Log.e("hh  ",url);
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("response",response);
                    JSONObject obj=new JSONObject(response);
                    JSONArray user=obj.getJSONArray(shr.getvalue("userid").toString());
                    item=new ArrayList<>();
                    amount=new ArrayList<>();
                    for(int i=0;i<user.length();i++)
                    {
                        JSONObject o1=user.getJSONObject(i);
                        if(o1.getString("payment status").equals("pending")) {
                            String finetype = o1.getString("finetype");
                            amount.add(o1.getInt("amount"));
//                        Date date= (Date) o1.get("date");
                            item.add(finetype);
                        }
                    }

                    final ListAdapter listAdapter=new CustomAdapter(getApplicationContext(),item,amount);
                    l1.setAdapter(listAdapter);
                    l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Log.e("message",position+"");
                            int cost=amount.get(position);
                            Log.e("amount",cost+""+"bb"+adapterView.getItemAtPosition(position));
                            String temp=item.get(position);
                            shr.setvalue("fine_amount",cost);
                            if(temp.equals("not having registration card")){
//                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
//                                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),876767877,intent,0);
//                                Notification notification=new Notification.Builder(getApplicationContext()).setContentText("You have a notification").setContentTitle("App").setContentIntent(pendingIntent).addAction(R.drawable.ic_launcher,"reply",pendingIntent).build();
//                                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                                notification.flags |=Notification.FLAG_AUTO_CANCEL;
//                                notificationManager.notify(0,notification);
                                    Log.e("Notification","nnnnn");
//                                NotificationCompat.Builder builder = new NotificationCompat.Builder(User_Payment_List.this);
//                                builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
//                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
//                                PendingIntent pendingIntent = PendingIntent.getActivity(User_Payment_List.this, 0, intent, 0);
//                                builder.setContentIntent(pendingIntent);
//                                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                                builder.setContentTitle("Notifications Title");
//                                builder.setContentText("Your notification content here.");
//                                builder.setSubText("Tap to view the website.");
//
//                                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                                // Will display the notification in the notification bar
//                                notificationManager.notify(1, builder.build());

//                                Intent intent1 = new Intent(User_Payment_List.this, Webview.class);
//                                //Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW,
//                                  //      Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
//                                PendingIntent pendingIntent = PendingIntent.getActivity(User_Payment_List.this, 876767877, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//                                Notification notification = new Notification.Builder(User_Payment_List.this).setContentText("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345").setSmallIcon(R.drawable.ic_launcher).setContentTitle("The distance is:").setContentIntent(pendingIntent).setAutoCancel(true).build();
//                                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                                notification.flags |= Notification.FLAG_AUTO_CANCEL;
//                                notificationManager.notify(0, notification);

                               /* Intent intent = new Intent(getApplicationContext(), Webview.class);
                                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                                NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext());

                                b.setAutoCancel(true)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .setWhen(System.currentTimeMillis())
                                        .setSmallIcon(R.drawable.ic_launcher)
                                        .setTicker("Hearty365")
                                        .setContentTitle("Default notification")
                                        .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                                        .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                                        .setContentIntent(contentIntent)
                                        .setContentInfo("Info")
                                .setChannelId("default_channel_id");


                                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                notificationManager.notify(1, b.build());
*/
                            }
                            Intent intent=new Intent(User_Payment_List.this,Webview.class);
                            intent.putExtra("amount",cost);
                            startActivity(intent);
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse (VolleyError error){
                Log.e("error", "volley");
            }
        });
        queue.add(stringRequest);

    }
 /*   public void list_clicked(View view)
    {
        Log.e("list","clicked");
        Intent intent=new Intent(User_Payment_List.this,Webview.class);
        startActivity(intent);
    }*/
 @Override
 public void onBackPressed() {
     Intent i=new Intent(getApplicationContext(),Udashboard.class);
     startActivity(i);
     this.finish();

 }
}