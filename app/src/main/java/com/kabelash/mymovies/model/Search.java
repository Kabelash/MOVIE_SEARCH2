package com.kabelash.mymovies.model;

import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Title")
    public String Title="";

    @SerializedName("Year")
    public String Year="";

    @SerializedName("imdbID")
    public String imdbID="";

    @SerializedName("Type")
    public String Type="";

    @SerializedName("Poster")
    public String Poster="";

    public Search() {
    }

    public Search(String Title, String Year, String imdbID, String Type, String Poster) {
        this.Title = Title;
        this.Year = Year;
        this.imdbID = imdbID;
        this.Type = Type;
        this.Poster = Poster;
    }

}
