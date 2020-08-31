package com.example.filmfinder.model.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmfinder.R;
import com.example.filmfinder.adapter.FilmAdapter;
import com.example.filmfinder.model.Film;
import com.example.filmfinder.network.FilmApp;
import com.example.filmfinder.network.FilmResponse;
import com.example.filmfinder.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularFragment extends Fragment {

    private int page = 1;
    private String API_KEY = "71dd7f88c9be5e56f400fa680f1ead0a";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popular, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerview_id);
        final LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        Service apiService = FilmApp.getInstance().service;
        Call<FilmResponse> call = apiService.getPopularFilms(API_KEY, "ru",page);
        call.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                List<Film> films = response.body().getResults();
                recyclerView.setAdapter(new FilmAdapter(getContext(), films));
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                    //Добавляем реализацию слушателя скролла.
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == 19) {

                            //Прибавляем страницу при нахождении последнего элемента на предыдущей странице
                            Call <FilmResponse> call = apiService.getPopularFilms(API_KEY,"ru", page+=1);
                            //Заводим новый call
                            call.enqueue(new Callback<FilmResponse>() {
                                @Override
                                public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                                    films.addAll(response.body().getResults());
                                    recyclerView.getAdapter().notifyItemRangeInserted(films.size(),20);
                                }

                                @Override
                                public void onFailure(Call<FilmResponse> call, Throwable t) {

                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                Log.e("Popular Fragment ",t.toString());
            }
        });
    }
}
