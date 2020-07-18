package com.example.filmfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.filmfinder.adapter.RecyclerViewAdapter;
import com.example.filmfinder.model.Film;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    List<Film> lstFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        lstFilm = new ArrayList<>();
        lstFilm.add(new Film("Остров проклятых", "Два американских судебных пристава отправляются на один из островов в штате Массачусетс, чтобы расследовать исчезновение пациентки клиники для умалишенных преступников. При проведении расследования им придется столкнуться с паутиной лжи, обрушившимся ураганом и смертельным бунтом обитателей клиники.", R.drawable.island));
        lstFilm.add(new Film("Космический джем", "«Космический джем» — семейный комедийный фильм режиссёра Джо Питка 1996 года о приключениях баскетболиста Майкла Джордана.", R.drawable.jam));
        lstFilm.add(new Film("Детектив Пикачу", "Киноадаптация сверхпопулярного аниме о волшебных существах, каждый из которых наделен уникальными способностями. «Талисман» франшизы Пикачу примерит шляпу Шерлока Холмса, взявшись за расследование таинственных событий, связанных с покемонами.", R.drawable.pokemon));
        lstFilm.add(new Film("Властелин Колец", "Последняя часть трилогии о Кольце Всевластия и о героях, взявших на себя бремя спасения Средиземья. Повелитель сил Тьмы Саурон направляет свои бесчисленные рати под стены Минас-Тирита, крепости Последней Надежды. Он предвкушает близкую победу, но именно это и мешает ему заметить две крохотные фигурки — хоббитов, приближающихся к Роковой Горе, где им предстоит уничтожить Кольцо Всевластия. Улыбнется ли им счастье", R.drawable.ring));
        lstFilm.add(new Film("Человек-Паук: Через вселенные", "В центре сюжета уникальной и инновационной в визуальном плане картины подросток из Нью-Йорка Майлз Моралес, который живет в мире безграничных возможностей вселенных Человека-паука, где костюм супергероя носит не только он.", R.drawable.spider));
        lstFilm.add(new Film("Доктор Стрэндж", "Страшная автокатастрофа поставила крест на карьере успешного нейрохирурга Доктора Стрэнджа. Отчаявшись, он отправляется в путешествие в поисках исцеления и открывает в себе невероятные способности к трансформации пространства и времени. Теперь он — связующее звено между параллельными измерениями, а его миссия — защищать жителей Земли и противодействовать Злу, какое бы обличие оно ни принимало.", R.drawable.strange));
        lstFilm.add(new Film("Тор: Рагнарек", "Вернувшись в Асгард в поисках таинственного врага, ведущего охоту на Камни Бесконечности, Тор обнаруживает, что действия его брата Локи, захватившего трон Асгарда, привели к приближению наиболее страшного события — Рагнарёка. По легенде, это ознаменует последнюю битву Асгарда, последствием которой станет его полное уничтожение. В попытке предотвратить это событие, Тору предстоит прибегнуть к помощи своего товарища из Мстителей — Халка. Вместе, им предстоит столкнуться лицом к лицу со злейшим врагом всех девяти миров — огненным демоном Суртуром, целью которого является исполнение пророчества о Рагнарёке и уничтожение девяти миров.", R.drawable.thor));

        RecyclerView myrc = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstFilm);
        myrc.setLayoutManager(new GridLayoutManager(this, 2));
        myrc.setAdapter(myAdapter);
    }

        //Кнопки у drawer'a
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_top_rated:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PopularFragment()).commit();
                break;
            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoriteFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Делимся",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InfoFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        //Закрываем drawer кнопкой Назад, если он открыт
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}