package com.example.movies.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesReply implements Parcelable {

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> results;
    public final static Creator<MoviesReply> CREATOR = new Creator<MoviesReply>() {

        public MoviesReply createFromParcel(Parcel parcel) {
            return new MoviesReply(parcel);
        }

        public MoviesReply[] newArray(int i) {
            return (new MoviesReply[i]);
        }
    };

    protected MoviesReply(Parcel parcel) {

        page = parcel.readInt();
        totalResults = parcel.readInt();

        totalPages = parcel.readInt();

        parcel.readList(this.results, (Movie.class.getClassLoader()));
    }

    public MoviesReply() {
    }

    public MoviesReply(int page, int totalResults, int totalPages, ArrayList<Movie> movies) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = movies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public MoviesReply withPage(int page) {
        this.page = page;
        return this;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public MoviesReply withTotalResults(int totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public MoviesReply withTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public ArrayList<Movie> getMovies() {
        return results;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.results = movies;
    }

    public MoviesReply withMovies(ArrayList<Movie> movies) {
        this.results = movies;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(page);
        parcel.writeValue(totalResults);
        parcel.writeValue(totalPages);
        parcel.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
