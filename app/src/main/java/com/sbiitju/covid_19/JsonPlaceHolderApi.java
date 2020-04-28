package com.sbiitju.covid_19;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("v2/countries/")
    Call<List<Mode>> getPosts();
}