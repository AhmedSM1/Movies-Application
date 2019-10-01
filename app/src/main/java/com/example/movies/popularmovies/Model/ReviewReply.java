package com.example.movies.popularmovies.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewReply {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<Review> results = null;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Review> getReviews() {
        return results;
    }

    public void setReviews(List<Review> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}