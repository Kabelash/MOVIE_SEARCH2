package com.kabelash.mymovies.network;

import com.kabelash.mymovies.model.MovieDetails;
import com.kabelash.mymovies.model.MovieSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //To search movies
    @GET("?apikey=165bdef")
    Call<MovieSearch> getmoviedata(@Query("s") String sQuery);

    //To view movie details
    @GET("?apikey=165bdef")
    Call<MovieDetails> getmoviedetails(@Query("i") String mId);
}
