package com.sbiitju.covid_19;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.SparseLongArray;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.PortUnreachableException;


public class SplashScreen extends AppCompatActivity {

    //    TextView textView1,textView2,s;

    int i;
    FirebaseAuth firebaseAuth;
    ProgressDialog progress,progressDialog;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        progress=new ProgressDialog(SplashScreen.this);

        progress.setTitle("Pashe Achi");
        progress.setMessage("Connection Checking.....");
        progress.setIcon(R.drawable.pasheasi);
        progress.show();

//        textView1=findViewById(R.id.tex1);
//        textView2=findViewById(R.id.tex2);
//        s=findViewById(R.id.sobujbus);

        firebaseAuth=FirebaseAuth.getInstance();
//        logo.setAnimation(animation3);
//        textView1.startAnimation(animation3);
//        textView2.startAnimation(animation3);
        //s.startAnimation(animation3);
        //logo.startAnimation(animation2);

        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                dowork();
                boolean state=isNetworkAvailable();
                if(state==true){
                    startActivity(new Intent(SplashScreen.this,MainTask.class));
                    finish();
//                    newact();
                }else {
                    progress.dismiss();
                }

            }
        });
        thread.start();
    }
    public void dowork(){
        for(i=0;i<=100;i=i+25) {
            try {
                Thread.sleep(500);
                try{
//                    progressBar.setProgress(i);

                }catch (NullPointerException ignored){

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void newact(){
//        startActivity(new Intent(this,MainTaskForOthers.class));
        if(firebaseAuth.getCurrentUser()!=null){
            final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid());

            databaseReference.child("Profile").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        startActivity(new Intent(SplashScreen.this,MainTask.class));
                        finish();

                    }
                    else {
                        startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}