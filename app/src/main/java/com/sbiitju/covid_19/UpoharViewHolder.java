package com.sbiitju.covid_19;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UpoharViewHolder extends RecyclerView.ViewHolder {
    Button donate;
    ImageView imageView;
    TextView brief,title;
    public UpoharViewHolder(@NonNull View itemView) {
        super(itemView);
        donate=itemView.findViewById(R.id.upoharcardbtn);
        imageView=itemView.findViewById(R.id.upoharcardpic);
        brief=itemView.findViewById(R.id.upoharcardetailes);
        title=itemView.findViewById(R.id.upoharcardtitle);
    }
}
