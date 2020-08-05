package com.example.filmfinder.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/top_rated")
    Call<FilmResponse> getTopRatedFilms(@Query("api_key")String apiKey, @Query("language")String language, @Query("page") int page);

    @GET("movie/popular")
    Call<FilmResponse> getPopularFilms(@Query("api_key")String apiKey, @Query("language")String language, @Query("page") int page);

    @GET("movie/{id}")
    Call<FilmResponse> getMovieDetails(@Path("id") int id, @Query("api_key")String apiKey);

}
