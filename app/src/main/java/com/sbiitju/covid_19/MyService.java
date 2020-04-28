//package com.sbiitju.covid_19;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.IBinder;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.core.app.NotificationCompat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//public class MyService extends Service {
//    String value;
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        value=intent.getStringExtra("value");
//        return null;
//    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public Notification myforgroundservice(){
//        Intent intent = new Intent(this,MyService.class);
//        startService(intent);
//        startForegroundService(new Intent(new Intent(MyService.this,MyService.class)));
//        Intent notificationintent=new Intent(MyService.this,MainTask.class);
//        notificationintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent=PendingIntent.getActivity(MyService.this,0,notificationintent,PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationChannel channel=new NotificationChannel("Shahin Bashar", "IIT-46", NotificationManager.IMPORTANCE_HIGH);
//        NotificationManager string=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        string.createNotificationChannel(channel);
//        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"shahin")
//                .setContentText(value).setContentTitle(value).setOngoing(true)
//                .setPriority(Notification.PRIORITY_HIGH)
//                .setSmallIcon(R.drawable.pasheasi)
//                .setColor(Color.GREEN)
//                .setWhen(System.currentTimeMillis());
//        builder.setContentIntent(pendingIntent);
//        builder.setChannelId("Shahin Bashar");
//        return builder.build();
//    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void onCreate() {
//        startForeground(1, myforgroundservice());
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for(int i=0;i<1000000000;i++){
//                    System.out.println(String.valueOf(i));
//
////                    startService(new Intent(MyService.this,MyService.class));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if(i==10){
//                        i=9;
//                    }
//                }
//
//            }
//
//        }).start();
//        super.onCreate();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if(intent != null){
//            Bundle bundle = intent.getExtras();
//            value = bundle.getString("link");
//        }
//        return super.onStartCommand(intent, flags, startId);
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//}
