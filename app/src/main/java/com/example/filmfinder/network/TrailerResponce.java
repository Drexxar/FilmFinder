package com.example.filmfinder.network;

import com.example.filmfinder.model.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponce {

    @SerializedName("id")
    private int idTrailer;
    @SerializedName("results")
    private List<Trailer> results;

    public int getIdTrailer() {
        return idTrailer;
    }

    public void setIdTrailer(int idTrailer) {
        this.idTrailer = idTrailer;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
