package com.example.filmfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmfinder.R;
import com.example.filmfinder.model.Film;
import com.example.filmfinder.model.FilmActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private Context mContext;
    private List<Film> mData;

    public FilmAdapter(Context mContext, List<Film> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_film, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvFilmTitle.setText(mData.get(position).getTitle());

        Picasso.with(mContext)
                .load(mData.get(position).getPosterPath())
                .fit()
                .centerCrop()
                .into(holder.ivFilmThumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent (mContext, FilmActivity.class);

                intent.putExtra("Title", mData.get(position).getTitle());
                intent.putExtra("Description", mData.get(position).getOverview());
                intent.putExtra("Thumbnail", mData.get(position).getPosterPath());
                intent.putExtra("Id", mData.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFilmTitle;
        ImageView ivFilmThumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvFilmTitle = itemView.findViewById(R.id.film_title_id);
            ivFilmThumbnail = itemView.findViewById(R.id.film_image_id);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }


}
