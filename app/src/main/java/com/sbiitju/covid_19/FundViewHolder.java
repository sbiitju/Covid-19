package com.sbiitju.covid_19;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FundViewHolder extends RecyclerView.ViewHolder {
    TextView name,amount,type;
    public FundViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.fname);
        amount=itemView.findViewById(R.id.famount);
        type=itemView.findViewById(R.id.freason);
    }
}
