package com.kabelash.mymovies.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.kabelash.mymovies.R;
import com.kabelash.mymovies.model.Search;
import com.kabelash.mymovies.ui.adapter.MovieAdapter;
import com.kabelash.mymovies.viewmodel.MyListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterListener {

    private RecyclerView recyclerview;
    private MyListViewModel myListViewModel;
    private MovieAdapter adapter;
    private SearchView searchView;
    ProgressBar pBar;
    TextView tvs;
    public String searchQuery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.recyclerView);
        myListViewModel = ViewModelProviders.of(this).get(MyListViewModel.class);

        //Setting Progress Bar & textview
        pBar = findViewById(R.id.progressBar);
        pBar.setVisibility(View.GONE);
        tvs = findViewById(R.id.tvSearch);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                searchQuery = query;
                pBar.setVisibility(View.VISIBLE);
                tvs.setVisibility(View.GONE);
                // refreshing view model & setting recycler view
                updateUi();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                searchQuery = query;

                if (tvs.getVisibility() == View.GONE) {
                    adapter.getFilter().filter(query);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //To call searchview
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void updateUi() {
        myListViewModel.getSearchResults(searchQuery).observe(MainActivity.this, new Observer<ArrayList<Search>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Search> myListViewModels) {
                // handle changes here, to use the data write code below
                adapter = new MovieAdapter(myListViewModels, MainActivity.this);
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(adapter);

                //Hiding progress bar
                pBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onMovieSelected(Search movieSearch) {
    }
}
