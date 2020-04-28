package com.sbiitju.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FundAdapter extends RecyclerView.Adapter<FundViewHolder> {
    Context context;
    ArrayList<FundInfo> arrayList;
    public FundAdapter(Context context,ArrayList<FundInfo> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public FundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.funddetailes,parent,false);
        return new FundViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FundViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.amount.setText(arrayList.get(position).getAmount()+" Tk");
        holder.type.setText(arrayList.get(position).getReason());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
