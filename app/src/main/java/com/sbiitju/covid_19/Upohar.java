package com.sbiitju.covid_19;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Upohar extends Fragment {
    ArrayList<UpoharModel> arrayList;
    RecyclerView recyclerView;
    ArrayList<FundInfo> fundarry;
    Button askfor,checkblnc,aboutfoundation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_upohar, container, false);
        arrayList=new ArrayList<>();
        fundarry=new ArrayList<>();
        recyclerView=root.findViewById(R.id.upoharrecaycler);
        String cbrief="We have begun Emergency COVID-19 campaign for the most vulnerable people in Bangladesh: disabled, jobless, hungry, elderly, and orphans. We are working for ensuring safety of the society.";
        String cdes="We are all completely acknowledged about the poor socio-economic condition of Bangladesh. COVID-19 has made the situation even worse by putting an already poverty stricken population on lockdown where accessibility to nutritional food, drinking water, products which are necessary to maintain hygienic life-style, medicines and  other daily commodities are scarcer day by day. Street vendors, hourly wage earners, shopkeepers, and daily laborers are suffering from starvation. This project is addressing to cope up with the emergency need with distribution of survival kits to thousands of people who need it most.";
        UpoharModel covid= new UpoharModel("Let's Fight Against COVID-19",cbrief+cdes,cdes,R.drawable.koyen);
        String rdes="100,000 Ramadan Iftar-Sehri Basket distribution that exactly we are planning to distribute food in this holy month among slum and homeless peoples. Each bag will contains 8 kg of rice-flour, 1 kg of chickpea, salt, sugar, oil, milk powder, and biscuit. Due to the pandemic, millions of people are jobless during this crucial moment. Please donate to support this helpless Muslims.";
        String rbrief;
        UpoharModel iftar= new UpoharModel("IFTAR Basket For Homeless",rdes,rdes,R.drawable.iftar);
        String zdes="Rehabilitation project for jobless hawkers and farmers badly needs your Zakat fund. Millions of small businesses used their capitals to survive during the pandemic. Please donate your Zakat fund for farmers to buy seeds, sewing machine for widows, cow-calf for poor villagers. Your fund will properly be used under Islamic scholars. And you will get a audited report in our page.";

        UpoharModel zakat= new UpoharModel("ZAKAT For Unemployed Muslims",zdes,zdes,R.drawable.zakat);

        arrayList.add(covid);
        arrayList.add(iftar);
        arrayList.add(zakat);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        UpoharAdapter upoharAdapter=new UpoharAdapter(getContext(),arrayList);
        recyclerView.setAdapter(upoharAdapter);
        askfor=root.findViewById(R.id.askforfund);
        checkblnc=root.findViewById(R.id.checkbalance);
        aboutfoundation=root.findViewById(R.id.aboutit);
        askfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=getLayoutInflater().inflate(R.layout.askforcard, null);
                final EditText name,number,address,problem;
                final AutoCompleteTextView district;
                final Spinner askspn;
                Button submit;
                askspn=view1.findViewById(R.id.askspiner);
                name=view1.findViewById(R.id.askname);
                number=view1.findViewById(R.id.asknumber);
                address=view1.findViewById(R.id.askaddress);
                problem=view1.findViewById(R.id.askproblem);
                district=view1.findViewById(R.id.askdistrict);
                ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.district));
                district.setThreshold(1);//will start working from first character
                district.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                district.setTextColor(Color.RED);
                submit=view1.findViewById(R.id.asksubmit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog mProgressDialog;
                        mProgressDialog=new ProgressDialog(getContext());
                        final String na,nu,add,pr,dis,sp;
                        na=name.getText().toString();
                        nu=number.getText().toString();
                        add=address.getText().toString();
                        pr=problem.getText().toString();
                        dis=district.getText().toString();
                        sp=askspn.getSelectedItem().toString();
                        if(na.equals("")||nu.equals("")||add.equals("")||dis.equals("")||pr.equals("")||dis.equals("")){
                            name.setError("Please Fill Up All the Box");
                        }
                        else {
                            mProgressDialog.show();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            Ask ask=new Ask(na,nu,dis,add,pr,sp,currentDateandTime);
                            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("AskDonation");
                            databaseReference.child(sp).child(dis).child(nu).setValue(ask).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        mProgressDialog.dismiss();
                                        startActivity(new Intent(getContext(),MainTask.class));
                                    }
                                }
                            });


                        }


                    }
                });
                builder.setView(view1).show();
            }
        });
        aboutfoundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=getLayoutInflater().inflate(R.layout.aboutfoundation, null);
                final RecyclerView recyclerView1;
                recyclerView1=view1.findViewById(R.id.frecyclr);
                final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("FundInfo");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if(dataSnapshot.exists()){
                       fundarry.clear();
                       for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                           FundInfo fundInfo=dataSnapshot1.getValue(FundInfo.class);
                           fundarry.add(fundInfo);
                       }
                       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                       linearLayoutManager.setSmoothScrollbarEnabled(true);
                       linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                       recyclerView1.setLayoutManager(linearLayoutManager);
                       FundAdapter fundAdapter=new FundAdapter(getContext(),fundarry);
                       recyclerView1.setAdapter(fundAdapter);
                   }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                builder.setView(view1).show();

            }
        });
        checkblnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=getLayoutInflater().inflate(R.layout.checkblnc, null);
                final EditText number,amount;
                final Spinner spinner;
                Button submit;
                number=view1.findViewById(R.id.number);
                amount=view1.findViewById(R.id.email);
                spinner=view1.findViewById(R.id.spinner);
                submit=view1.findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String nu=number.getText().toString();
                        String s=spinner.getSelectedItem().toString();
                        final String a=amount.getText().toString();
                        if(a.equals("")||nu.equals("")){
                            number.setError("Fill Up All the box");
                        }
                        else{
                            final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Donation");
                            databaseReference.child(s).child(nu).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        DonationData donationData=dataSnapshot.getValue(DonationData.class);
                                        String cnu=donationData.getNumber();
                                        String cam=donationData.getAmount();
                                        AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
                                        if(cnu.equals(nu)&&a.equals(cam)){
                                            builder1.setTitle("Dear "+donationData.getName());
                                            builder1.setMessage("You have donated "+donationData.getAmount()+"TK"+"\n"+"Thank You" );
                                            builder1.show();
                                        }
                                        else{
                                            builder1.setTitle("Sorry!!");
                                            builder1.setMessage("May be Your donation is not counted"+"\n"+"if you did it"+"\n"+"it'll be counted as soon as possible"+"\n"+"Thank You");
                                            builder1.show();
                                        }
                                    }
                                    else {
                                        AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
                                        builder1.setTitle("No Data Found");
                                        builder1.setMessage("Please input correct value").show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                    }
                });
                builder.setView(view1).show();
            }
        });
    return root;
    }


}
