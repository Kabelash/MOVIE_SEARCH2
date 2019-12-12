package com.kabelash.mymovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieSearch {

    @SerializedName("Search")
    public ArrayList<Search> Search =null;

    public MovieSearch() {
    }

}
