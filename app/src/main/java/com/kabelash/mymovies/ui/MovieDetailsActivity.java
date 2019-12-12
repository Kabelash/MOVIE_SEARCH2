package com.kabelash.mymovies.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.mymovies.R;
import com.kabelash.mymovies.model.MovieDetails;
import com.kabelash.mymovies.ui.adapter.MovieDetailsAdapter;
import com.kabelash.mymovies.viewmodel.MyListViewModel;

public class MovieDetailsActivity  extends AppCompatActivity  implements MovieDetailsAdapter.MovieDetailsAdapterListener{

    private MyListViewModel movieDetailsViewModel;
    private RecyclerView recyclerview2;
    private MovieDetailsAdapter adapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);

        //Getting id from string extra
        String imdbID = getIntent().getStringExtra("IMDB_ID");

        //Setting recyclerview
        recyclerview2 = findViewById(R.id.recyclerView2);
        movieDetailsViewModel = ViewModelProviders.of(this).get(MyListViewModel.class);
        movieDetailsViewModel.getMovieDetails(imdbID).observe(MovieDetailsActivity.this, new Observer<MovieDetails>() {

            @Override
            public void onChanged(MovieDetails movieDetails) {
                adapter2 = new MovieDetailsAdapter(movieDetails, MovieDetailsActivity.this);
                recyclerview2.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this));
                recyclerview2.setItemAnimator(new DefaultItemAnimator());
                recyclerview2.setAdapter(adapter2);
            }
        });
    }


}
