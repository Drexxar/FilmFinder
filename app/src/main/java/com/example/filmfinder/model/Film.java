package com.example.filmfinder.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Film {

    @SerializedName("adult")
    private boolean adult;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private  String overview;
    @SerializedName("release_date")
    private int releaseDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String OriginalTitle;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Film(boolean adult, String posterPath, String overview, int releaseDate, List<Integer> genreIds,
                Integer id, String originalTitle, String title, boolean video, Double voteAverage) {
        this.adult = adult;
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        OriginalTitle = originalTitle;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    String baseImageUrl ="https://image.tmdb.org/t/p/w500";

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getPosterPath() {
        return baseImageUrl.concat(posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        OriginalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
