package com.kabelash.mymovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.mymovies.R;
import com.kabelash.mymovies.databinding.MovieDetailsBinding;
import com.kabelash.mymovies.model.MovieDetails;

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {

    private MovieDetails movieDetails;
    private Context context2;
    private LayoutInflater layoutInflater2;

    public MovieDetailsAdapter(MovieDetails movieDetails, Context context) {
        this.context2 = context;
        this.movieDetails = movieDetails;
    }

    @NonNull
    @Override
    public MovieDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater2 == null) {
            layoutInflater2 = LayoutInflater.from(parent.getContext());
        }
        MovieDetailsBinding movieDetailsBinding = DataBindingUtil.inflate(layoutInflater2, R.layout.movie_details, parent, false);
        return new MovieDetailsAdapter.ViewHolder(movieDetailsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailsAdapter.ViewHolder holder, final int position) {
        final MovieDetails movieDetailsViewModel = movieDetails;
        holder.bind(movieDetailsViewModel);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public interface MovieDetailsAdapterListener {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MovieDetailsBinding movieDetailsBinding;

        public ViewHolder(@NonNull MovieDetailsBinding movieDetailsBinding) {
            super(movieDetailsBinding.getRoot());
            this.movieDetailsBinding = movieDetailsBinding;
        }

        public void bind(MovieDetails myli) {
            this.movieDetailsBinding.setMoviedetailsmodel(myli);
            movieDetailsBinding.executePendingBindings();
        }

        public MovieDetailsBinding getMovieDetailsBinding() {
            return movieDetailsBinding;
        }
    }
}
