package com.example.filmfinder.model.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.filmfinder.R;
import com.example.filmfinder.adapter.RecyclerViewAdapter;
import com.example.filmfinder.model.Film;
import com.example.filmfinder.network.FilmApp;
import com.example.filmfinder.network.FilmResponse;
import com.example.filmfinder.network.Service;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private int page = 1;
    private int limit = 10;
    private String API_KEY = "71dd7f88c9be5e56f400fa680f1ead0a";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Service apiService = FilmApp.getInstance().service;
        Call<FilmResponse> call = apiService.getTopRatedFilms(API_KEY, "ru", 1);
        call.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call,Response<FilmResponse> response) {
                List<Film> films = response.body().getResults();
                    recyclerView.setAdapter(new RecyclerViewAdapter(getContext(), films));
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                    Log.e("Home Fragment ", t.toString());
            }
        });
    }
}
