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

     public TrailerAdapter( List<Trailer> trailerList) {
        this.trailerList = trailerList; }

    @NonNull
    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item,viewGroup,false);
        return new MyViewHolder(view);
    }
    //"https://www.youtube.com/watch?v="
    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.MyViewHolder myViewHolder, final int i) {
         Trailer trailer = trailerList.get(i);
         final String key = trailer.getKey();


        Picasso.get()
                .load("https://img.youtube.com/vi/" + key + "/0.jpg")
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


                        Log.d(TAG, "Key : " + trailer.getKey() + "name:" + trailer.getName());
        Log.d(TAG,"http://img.youtube.com/vi/"+ key + "/0.jpg");

        myViewHolder.trailerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=" + key));
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
