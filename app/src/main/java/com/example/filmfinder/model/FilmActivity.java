package com.example.filmfinder.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmfinder.R;
import com.squareup.picasso.Picasso;

public class FilmActivity extends AppCompatActivity {

    private Context mContext;
    private TextView tvtitle, tvdescription;
    private ImageView img;

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
    }
}