package com.example.filmfinder.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmfinder.R;
import com.example.filmfinder.adapter.TrailerAdapter;
import com.example.filmfinder.network.FilmApp;
import com.example.filmfinder.network.Service;
import com.example.filmfinder.network.TrailerResponce;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmActivity extends AppCompatActivity {

    private Context mContext;
    private TextView tvtitle, tvdescription;
    private ImageView img;
    private RecyclerView recyclerView;
    private TrailerAdapter adapter;
    private List<Trailer> trailerList;
    private String API_KEY = "71dd7f88c9be5e56f400fa680f1ead0a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        tvtitle = findViewById(R.id.film_title);
        tvdescription = findViewById(R.id.film_description);
        img = findViewById(R.id.film_thumbnail);

        //Принимаем данные
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String Image = intent.getExtras().getString("Thumbnail");

        //Настраиваем значения
        tvtitle.setText(Title);
        tvdescription.setText(Description);
        Glide.with(img.getContext())
                .load(Image)
                .into(img);

        initViews();


    }

    private void initViews(){
        trailerList = new ArrayList<>();
        adapter = new TrailerAdapter(this, trailerList);

        recyclerView = findViewById(R.id.trailer_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSON();
    }

    private void loadJSON(){
        int filmId = getIntent().getExtras().getInt("Id");
        Service apiService = FilmApp.getInstance().service;
        Call <TrailerResponce> call = apiService.getFilmTrailer(filmId, API_KEY);
        call.enqueue(new Callback<TrailerResponce>() {
            @Override
            public void onResponse(Call<TrailerResponce> call, Response<TrailerResponce> response) {
                List <Trailer> trailer = response.body().getResults();
                recyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), trailer));
            }

            @Override
            public void onFailure(Call<TrailerResponce> call, Throwable t) {
                Log.d("Ошибка", t.getMessage());
            }
        });
    }
}