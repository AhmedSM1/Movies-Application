package com.example.movies.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.popularmovies.Model.Trailer;
import com.example.movies.popularmovies.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder>{
    Context context;
    private List<Trailer> trailerList;
    public static final String TAG = TrailerAdapter.class.getName();
    public static final String YOUTUBE_IMG_PATH = "https://img.youtube.com/vi/";
    public static final String EXTENSION = "/0.jpg";
    public static final String YOUTUBE_PATH = "https://www.youtube.com/watch?v=";
     public TrailerAdapter( List<Trailer> trailerList,Context context) {
        this.trailerList = trailerList;
        this.context = context;
     }

    @NonNull
    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item,viewGroup,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.MyViewHolder myViewHolder, final int i) {
         Trailer trailer = trailerList.get(i);
         final String key = trailer.getKey();


        Picasso.get()
                .load( YOUTUBE_IMG_PATH + key + EXTENSION )
                .into(myViewHolder.trailerImg, new Callback() {
                            @Override
                            public void onSuccess() {
                               Log.d(TAG,"Trailer onSuccess");
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.d(TAG,"Trailer onError"+e.getMessage());
                            }
                        });


        myViewHolder.trailerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_PATH+key));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         public ImageView trailerImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerImg = itemView.findViewById(R.id.trailer_img);
            }



    }}
