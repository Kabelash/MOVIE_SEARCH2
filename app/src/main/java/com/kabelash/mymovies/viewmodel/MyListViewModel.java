package com.kabelash.mymovies.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.kabelash.mymovies.model.MovieDetails;
import com.kabelash.mymovies.model.MovieSearch;
import com.kabelash.mymovies.model.Search;
import com.kabelash.mymovies.network.MovieApi;
import com.kabelash.mymovies.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListViewModel extends ViewModel {
    public String Title = "";
    public String Poster = "";
    public MutableLiveData<List<Search>> mutableLiveData = new MutableLiveData<List<Search>>();
    public MutableLiveData<MovieDetails> mutableLiveData2 = new MutableLiveData<MovieDetails>();

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl)/*.apply(RequestOptions.circleCropTransform())*/.into(imageView);
    }
    public MyListViewModel() {

    }

    //To search movie list
    public MutableLiveData<ArrayList<Search>> getSearchResults(String searchQuery) {

        final MutableLiveData<ArrayList<Search>> localData = new MutableLiveData<>();

        MovieApi api = RetrofitClient.getInstance().getMyApi();
        Call<MovieSearch> call = api.getmoviedata(searchQuery);
        call.enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                ArrayList<Search> myList = response.body().Search;
                mutableLiveData.setValue(myList);
                localData.setValue(myList);
            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {

            }
        });

        return localData;
    }

    //To view movie details
    public MutableLiveData<MovieDetails> getMovieDetails(String movieId) {

        final MutableLiveData<MovieDetails> localData2 = new MutableLiveData<>();

        MovieApi api = RetrofitClient.getInstance().getMyApi();
        Call<MovieDetails> call = api.getmoviedetails(movieId);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails mDetails = response.body();
                mutableLiveData2.setValue(mDetails);
                localData2.setValue(mDetails);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });

        return localData2;
    }
}