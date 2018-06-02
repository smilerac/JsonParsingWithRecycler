package com.kthdvs.day2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aayeshs on 6/2/18.
 */

public class RetrofitClient {

    public static Retrofit retrofit = null;
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://vedisapp.berlin-webdesign-agentur.de/")
                    .addConverterFactory(GsonConverterFactory.create())//to convert from json to gson cuz retrofit uses gson
                    .build();
        }
        return retrofit;
    }
}
