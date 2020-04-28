package com.sbiitju.covid_19.ui.home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sbiitju.covid_19.Demo;
import com.sbiitju.covid_19.JsonPlaceHolderApi;
import com.sbiitju.covid_19.LatLon;
import com.sbiitju.covid_19.MainActivity;
import com.sbiitju.covid_19.Mode;
import com.sbiitju.covid_19.MyAdapter;
import com.sbiitju.covid_19.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private HomeViewModel homeViewModel;
    ListView listView;
    ArrayList<Demo> arrayList,sorteddata;
    ImageView imageView;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    boolean aBoolean=false;
    LinearLayout layout;
    FirebaseAuth firebaseAuth;
    String string;
    LatLon latLon;
    Sensor sensor;
    int user=0;
    LocationManager locationManager;
    LocationListener locationListener;

    int countrynumber[];
    AutoCompleteTextView autocompletetext;
    ImageView flag;
    ProgressDialog progressDialog;
    TextView countryname,continentname,activecase,death,totaldeath,totalrecoverd,
            totaltest,newrecoverd,criticalcase,newcase;
    Button lockdown,hospital,call,registration,fundraisin,reliefdistribution,live;
    TextView world;
    Button police;
    Button search;
    long  c,r,d;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        image=root.findViewById(R.id.move);

        lockdown=root.findViewById(R.id.lockdownstatus);
        hospital=root.findViewById(R.id.patient);
        police=root.findViewById(R.id.police);
        call=root.findViewById(R.id.emergencycall);
        registration=root.findViewById(R.id.registration);
        fundraisin=root.findViewById(R.id.fundraising);
        reliefdistribution=root.findViewById(R.id.reliefdistromap);
        live=root.findViewById(R.id.livecoroatest);
        search=root.findViewById(R.id.searcanycountry);
        world=root.findViewById(R.id.worldnews);
        sorteddata=new ArrayList<>();
        c=0;
        d=0;
        r=0;
        reliefdistribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("It's under Development");
                builder.setMessage("Coming soon..");
                builder.show();
            }
        });
        fundraisin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("It's under Development");
                builder.setMessage("Coming soon...");
                builder.show();
            }
        });
        lockdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                intent.putExtra("web", "https://corona.gov.bd/lockdown-status");
                startActivity(intent);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                intent.putExtra("web", "Nearest Hospital");
                startActivity(intent);

            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                intent.putExtra("web", "Nearest Police");
                startActivity(intent);

            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("It's under Development");
                builder.setMessage("Coming soon...");
                builder.show();
//                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
//                        View view1=getLayoutInflater().inflate(R.layout.cofounder, null);
//                final EditText name,number,email,
//                        subject,institute;
//                final AutoCompleteTextView distric;
//                name=view1.findViewById(R.id.cname);
//                number=view1.findViewById(R.id.cnumber);
//                email=view1.findViewById(R.id.cemail);
//                distric=view1.findViewById(R.id.cdistrict);
//                ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.district));
//                distric.setThreshold(1);//will start working from first character
//                distric.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                distric.setTextColor(Color.RED);
//                subject=view1.findViewById(R.id.subject);
//                institute=view1.findViewById(R.id.cinstitution);
//                Button submit=view1.findViewById(R.id.csubmit);
//                submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final String n, nu,e,d,su,i;
//                        n=name.getText().toString();
//                        nu=number.getText().toString();
//                        e=email.getText().toString();
//                        d=distric.getText().toString();
//                        if(e.equals("")||d.equals("")||nu.equals("")){
//                            email.setError("give me your valid info");
//                        }else {
//                            su=subject.getText().toString();
//                            i=institute.getText().toString();
//                            Cofounder cofounder=new Cofounder(n,nu,e,i,su,d);
//                            JavaMailAPI javaMailAPI=new JavaMailAPI(getContext(), e, "Verification", "Dear "+n+":\n We will contact you as early as possible.\n Thank You.");
//                            javaMailAPI.execute();
//                            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Cofounder");
//                            databaseReference.child(d).child(nu).setValue(cofounder).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        JavaMailAPI javaMailAPI1=new JavaMailAPI(getContext(), "shahinbashar2@gmail.com", "Want to be a Cofounder", n+"\n"+e+"\n"+nu);
//                                        javaMailAPI1.execute();
//                                        Toast.makeText(getContext(), "Your Request is submitted", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//                        }
//
//
//                    }
//                });
//                builder.setView(view1).show();

            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                intent.putExtra("web", "https://livecoronatest.com/");
                startActivity(intent);

            }
        });
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.animation);
        final Animation animation2= AnimationUtils.loadAnimation(getContext(),R.anim.anim2);
        Animation animation3= AnimationUtils.loadAnimation(getContext(),R.anim.anim3);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=getLayoutInflater().inflate(R.layout.call, null);
                Button a3,a16,a10;
                a3=view1.findViewById(R.id.a333);
                a16=view1.findViewById(R.id.a16263);
                a10=view1.findViewById(R.id.a10605);
                a3.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        dialContactPhone("333");
                    }
                });
                a16.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        dialContactPhone("16263");
                    }
                });
                a10.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        dialContactPhone("10605");
                    }
                });
                builder.setView(view1).show();

            }
        });



        autocompletetext=root.findViewById(R.id.auto);
//        image.setAnimation(animation);
//        dotask();
//        progressDialog=new ProgressDialog(getContext());
//        progressDialog.setTitle("Loading Data.....");
//        progressDialog.show();
        recyclerView=root.findViewById(R.id.recyclerview);
        arrayList=new ArrayList<>();

        flag=root.findViewById(R.id.countryflag);
        countryname=root.findViewById(R.id.countryname);
        continentname=root.findViewById(R.id.continentalname);
        activecase=root.findViewById(R.id.totalcase);
        totaldeath=root.findViewById(R.id.totaldathes);
        death=root.findViewById(R.id.newdathes);
        totalrecoverd=root.findViewById(R.id.recovered);
        totaltest=root.findViewById(R.id.totaltest);
        criticalcase=root.findViewById(R.id.criticalcase);
        newcase=root.findViewById(R.id.newcase);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Mode>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Mode>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Mode>> call, Response<List<Mode>> response) {
                List<Mode> posts = response.body();
                for (Mode post : posts) {
                    String url=post.getCountryInfo().getFlag();
                    Demo demo =new Demo(post.getCountry(),post.getContinent(),url,
                            post.getTests(),post.getCases(),post.getTodayCases(),
                            post.getDeaths(),post.getTodayDeaths(),
                            post.getRecovered(),post.getActive(),
                            post.getCritical(),post.getCasesPerOneMillion(),
                            post.getDeathsPerOneMillion());
                    arrayList.add(demo);
                }
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Data");
                databaseReference.setValue(arrayList);
                sorteddata.clear();
                for (int i=0;i<arrayList.size();i++){
                    Demo dm=arrayList.get(i);
                         c=c+dm.getCases();
                         d=d+dm.getDeaths();
                         r=r+dm.getRecovered();
                         if(dm.getCountry().equals("Bangladesh")){
                             sorteddata.add(dm);
                         }
                    if(dm.getCountry().equals("India")){
                        sorteddata.add(dm);
                    }
                    if(dm.getCountry().equals("Myanmar")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("China")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Bhutan")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Nepal")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Japan")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("France")){
                        sorteddata.add(dm);
                    }

                    if(dm.getCountry().equals("USA")){
                        sorteddata.add(dm);
                    }
                    if(dm.getCountry().equals("UK")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Sweden")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Sri Lanka")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Spain")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("Saudi Arabia")){
                        sorteddata.add(dm);
                    } if(dm.getCountry().equals("S. Korea")){
                        sorteddata.add(dm);
                    }


                }
                world.setText("World Live"+"\n"+"Total Infected: "+String.valueOf(c)+"\n"+"Total Deaths: "+String.valueOf(d)+"\n"+"Total Recovered: "+String.valueOf(r));
//                doforbd(arrayList, 15);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(getContext(),sorteddata);
                recyclerView.setAdapter(myAdapter);
                Demo d=arrayList.get(15);
                String value=d.getCountry()+"\n"+d.getActive();
//                intent.putExtra("value",value );
//                getContext().startForegroundService(intent);
//                getContext().startService(intent);

            }


            @Override
            public void onFailure(Call<List<Mode>> call, Throwable t) {
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=getLayoutInflater().inflate(R.layout.search, null);
                Button button=view1.findViewById(R.id.searchbtn);
                final AutoCompleteTextView autocompletetext=view1.findViewById(R.id.auto);
                ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countries_array));
                autocompletetext.setThreshold(1);//will start working from first character
                autocompletetext.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                autocompletetext.setTextColor(Color.RED);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String a=autocompletetext.getText().toString();
                        int value=check(a,arrayList);
                        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Data");
                        databaseReference.child(String.valueOf(value)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    Demo demo=dataSnapshot.getValue(Demo.class);
                                    showtheresult(demo);
                                }
                                else {
                                    Toast.makeText(getContext(), "No Data FOund", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                builder.setView(view1).show();
            }
        });
        return root;
    }

    private void showweb(final String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view1=getLayoutInflater().inflate(R.layout.webview, null);
        builder.setView(view1).show();
    }

    private void dotask(SearchView searchView) {

    }

//    public  void doforbd(ArrayList<Demo> arrayList1,int position){
//        newcase.setText("New Cases: "+String.valueOf(arrayList.get(position).getTodaycases()));
//        totaldeath.setText("Total Deaths: "+String.valueOf(arrayList.get(position).getDeaths()));
//        death.setText("New Deaths: "+String.valueOf(arrayList.get(position).getTodaydathes()));
//        criticalcase.setText("Critical Cases: "+String.valueOf(arrayList.get(position).getCritical()));
//        totaltest.setText("Total Tests: "+String.valueOf(arrayList.get(position).getTotaltests()));
//        countryname.setText(String.valueOf(arrayList.get(position).getCountry()));
//        totalrecoverd.setText("Total Recovered: "+String.valueOf(arrayList.get(position).getRecovered()));
//        continentname.setText("Total Infected: "+String.valueOf(arrayList.get(position).getActive()+arrayList.get(position).getRecovered()+arrayList.get(position).getDeaths()));
//        activecase.setText("Active Cases : "+String.valueOf(arrayList.get(position).getActive()));
//        Picasso.get().load(arrayList.get(position).getFlag()).into(flag);
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Data");
//        databaseReference.setValue(arrayList);
//
//    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void dialContactPhone(String number1) {
        Intent callIntent =new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number1));
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }
    private int check(String a, ArrayList<Demo> arrayList) {
        int v = 0;
        if(a.equals("United States")){
            a="USA";
        }
        if(a.equals("United Kingdom")){
            a="UK";
        }
        for(int j=0;j<arrayList.size();j++){
            if(arrayList.get(j).getCountry().equals(a)) {
            v=j;
            return v;
            }
        }

        return v;
    }
    private void showtheresult(Demo data) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View itemView=getLayoutInflater().inflate(R.layout.childview2,null);
        ImageView flag;
        TextView countryname,continentname,activecase,death,totaldeath,totalrecoverd,
                totaltest,newrecoverd,criticalcase,newcase;
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
        newcase.setText("New Cases: "+String.valueOf(data.getTodaycases()));
        totaldeath.setText("Total Deaths: "+String.valueOf(data.getDeaths()));
        death.setText("Today Deaths: "+String.valueOf(data.getTodaydathes()));
        criticalcase.setText("Critical Cases: "+String.valueOf(data.getCritical()));
        totaltest.setText("Total Tests: "+String.valueOf(data.getTotaltests()));
        countryname.setText(String.valueOf(data.getCountry()));
        totalrecoverd.setText("Total Recovered: "+String.valueOf(data.getRecovered()));
        continentname.setText("Total Infected: "+String.valueOf(data.getActive()+data.getRecovered()+data.getDeaths()));
        activecase.setText("Active Cases: "+String.valueOf(data.getActive()));
        Picasso.get().load(data.getFlag()).into(flag);
        builder.setView(itemView).show();
    }
    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                getContext(),
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            (Activity) getContext(),
                            new String[] { permission },
                            requestCode);
        }
        else {
            Toast
                    .makeText(getContext(),
                            "Permission already granted",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }



}
