package com.sbiitju.covid_19;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView flag;
    TextView countryname,continentname,activecase,death,totaldeath,totalrecoverd,
    totaltest,newrecoverd,criticalcase,newcase;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        flag=itemView.findViewById(R.id.countryflag);
        countryname=itemView.findViewById(R.id.countryname);
        continentname=itemView.findViewById(R.id.continentalname);
        activecase=itemView.findViewById(R.id.totalcase);
        totaldeath=itemView.findViewById(R.id.totaldathes);
        death=itemView.findViewById(R.id.newdathes);
        totalrecoverd=itemView.findViewById(R.id.recovered);
        totaltest=itemView.findViewById(R.id.totaltest);
        criticalcase=itemView.findViewById(R.id.criticalcase);
        newcase=itemView.findViewById(R.id.newcase);
    }
}
