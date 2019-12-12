package com.kabelash.mymovies.model;

import com.google.gson.annotations.SerializedName;

public class MovieDetails {

    @SerializedName("Title")
    private String Title = "";

    @SerializedName("Year")
    private String Year = "";

    @SerializedName("Released")
    private String Released = "";

    @SerializedName("Genre")
    private String Genre = "";

    @SerializedName("Director")
    private String Director = "";

    @SerializedName("Actors")
    private String Actors = "";

    @SerializedName("Poster")
    private String Poster = "";

    @SerializedName("imdbID")
    private String imdbID = "";

    @SerializedName("Production")
    private String Production = "";


    // Getter Methods
    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    public String getPoster() {
        return Poster;
    }

    public String getProduction() {
        return Production;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getReleased() {
        return Released;
    }

    // Setter Methods

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public void setReleased(String Released) {
        this.Released = Released;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setProduction(String Production) {
        this.Production = Production;
    }
}
