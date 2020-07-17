package com.example.filmfinder.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filmfinder.R;

public class FilmActivity extends AppCompatActivity {

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
        int Image = intent.getExtras().getInt("Thumbnail");

        //Настраиваем значения
        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(Image);
    }
}