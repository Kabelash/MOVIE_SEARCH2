package com.kabelash.mymovies.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //https://www.omdbapi.com/?i=tt3896198&apikey=165bdef&s=man
    private static final String BASE_URL="https://www.omdbapi.com/";
    private static RetrofitClient myClient;
    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (myClient==null){
            myClient=new RetrofitClient();
        }
        return myClient;
    }
    public MovieApi getMyApi(){
        return retrofit.create(MovieApi.class);
    }
}