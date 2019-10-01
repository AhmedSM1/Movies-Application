package com.example.movies.popularmovies.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.popularmovies.Model.Review;
import com.example.movies.popularmovies.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_item,viewGroup,false);
       return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder myViewHolder, int i) {
         Review review = reviews.get(i);

         myViewHolder.author.setText(review.getAuthor()+ ":");
         myViewHolder.content.setText(review.getContent());


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content , author ;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.txtAuthor);
            author.setPaintFlags(author.getPaintFlags()  | Paint.UNDERLINE_TEXT_FLAG);
            content = itemView.findViewById(R.id.txtReview);
        }
    }

}
