package com.sbiitju.covid_19;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UpoharAdapter extends RecyclerView.Adapter<UpoharViewHolder> {
    Context context;
    ArrayList<UpoharModel> arrayList;
    public UpoharAdapter(Context context,ArrayList<UpoharModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public UpoharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.upoharcard,parent,false);
        return new UpoharViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UpoharViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.imageView.setImageResource(arrayList.get(position).getImagedata());
        holder.brief.setText(arrayList.get(position).getDescription());
        holder.donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View view1=View.inflate(context,R.layout.donation, null);
                final EditText name,number,email;
                final Spinner spinner;
                Button submit;
                name=view1.findViewById(R.id.name);
                number=view1.findViewById(R.id.number);
                email=view1.findViewById(R.id.email);
                spinner=view1.findViewById(R.id.spinner);
                submit=view1.findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String na,nu,ty,em;
                        na=name.getText().toString();
                        nu=number.getText().toString();
                        em=email.getText().toString();
                        ty=spinner.getSelectedItem().toString();
                        final DonationData donationData=new DonationData(na,nu,em,"",ty);
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Donation");
                        databaseReference.child(ty).child(nu).setValue(donationData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    DatabaseReference databaseReference1= FirebaseDatabase.getInstance().getReference("FundInfo");
                                    FundInfo fundInfo=new FundInfo(donationData.getName(),donationData.getAmount(),donationData.getType());
                                    databaseReference1.child(donationData.getNumber()).setValue(fundInfo);
                                    String d="Please donate your desired amount to \n Bkash: 01701775747 \nRocket: 017245578348 \n Nagad: 01613162522\n Bank: AB Bank"+"\n"+"Savar Branch"+"\n"+"4029337080308 \n Savings Account\nfor more info call 01613162522\nor Visit https://www.facebook.com/sbiitju\nThank You";
//                                    JavaMailAPI javaMailAPI=new JavaMailAPI(context,em,"Donation Alert",d);
//                                    javaMailAPI.execute();
//                                    JavaMailAPI javaMailAPI1=new JavaMailAPI(context, "shahinbashar2@gmail.com", donationData.getName(),donationData.getNumber() );
//                                    javaMailAPI1.execute();
                                }
                            }
                        });
                    }
                });

                builder.setView(view1).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
