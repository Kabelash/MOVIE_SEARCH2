package com.kabelash.mymovies.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.mymovies.R;
import com.kabelash.mymovies.databinding.MyListBinding;
import com.kabelash.mymovies.model.Search;
import com.kabelash.mymovies.ui.MovieDetailsActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements Filterable {
    private ArrayList<Search> arrayList;
    private ArrayList<Search> movieListFiltered;
    private Context context;
    private LayoutInflater layoutInflater;
    private MovieAdapterListener listener;

    public MovieAdapter(ArrayList<Search> arrayList, Context context) {
        this.context = context;
        this.arrayList = arrayList;
        this.movieListFiltered = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MyListBinding myListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movielist, parent, false);
        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Search myListViewModel = movieListFiltered.get(position);
        holder.bind(myListViewModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myListViewModel.Title, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context.getApplicationContext(), MovieDetailsActivity.class);
                intent.putExtra("IMDB_ID", myListViewModel.imdbID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListFiltered.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MyListBinding myListBinding;

        public ViewHolder(@NonNull MyListBinding myListBinding) {
            super(myListBinding.getRoot());
            this.myListBinding = myListBinding;
        }

        public void bind(Search myli) {
            this.myListBinding.setMylistmodel(myli);
            myListBinding.executePendingBindings();
        }

        public MyListBinding getMyListBinding() {
            return myListBinding;
        }
    }

    // Filtering movies while typing
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    movieListFiltered = arrayList;
                } else {
                    ArrayList<Search> filteredList = new ArrayList<>();
                    for (Search row : arrayList) {

                        // Matching with movie title
                        if (row.Title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    movieListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<Search>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface MovieAdapterListener {
        void onMovieSelected(Search movieSearch);
    }
}