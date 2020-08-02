package com.example.filmfinder.network;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmApp extends Application {

    public Service service;
    private static FilmApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initRetrofit();
    }

    public static FilmApp getInstance() {
        return instance;
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);
    }
}
