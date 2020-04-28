package com.sbiitju.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<Demo> arrayList;
    public MyAdapter(Context context,ArrayList<Demo> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.childview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.newcase.setText("New Cases: "+String.valueOf(arrayList.get(position).getTodaycases()));
        holder.totaldeath.setText("Total Deaths: "+String.valueOf(arrayList.get(position).getDeaths()));
        holder.death.setText("New Deaths: "+String.valueOf(arrayList.get(position).getTodaydathes()));
        holder.criticalcase.setText("Critical Cases: "+String.valueOf(arrayList.get(position).getCritical()));
        holder.totaltest.setText("Total Tests: "+String.valueOf(arrayList.get(position).getTotaltests()));
        holder.countryname.setText(String.valueOf(arrayList.get(position).getCountry()));
        holder.totalrecoverd.setText("Total Recovered: "+String.valueOf(arrayList.get(position).getRecovered()));
        holder.continentname.setText("Total Infected: "+String.valueOf(arrayList.get(position).getActive()+arrayList.get(position).getRecovered()+arrayList.get(position).getDeaths()));
        holder.activecase.setText("Active Cases: "+String.valueOf(arrayList.get(position).getActive()));
        Picasso.get().load(arrayList.get(position).getFlag()).into(holder.flag);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Check", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
