package com.example.filmfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.filmfinder.R;
import com.example.filmfinder.model.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Trailer> trailerList;


    public  TrailerAdapter(Context mContext, List<Trailer> trailerList){
        this.mContext = mContext;
        this.trailerList = trailerList;
    }

    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trailer_card, viewGroup, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final TrailerAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(trailerList.get(i).getName());
    }

    @Override
    public int getItemCount(){
        return trailerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.trailer_description);
            thumbnail = view.findViewById(R.id.film_thumbnail);

            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                String videoId = trailerList.get(position).getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+videoId));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("VIDEO_ID", videoId);
                mContext.startActivity(intent);
            });
        }
    }

}
