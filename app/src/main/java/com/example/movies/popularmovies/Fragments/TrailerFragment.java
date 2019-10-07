package com.example.movies.popularmovies.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.popularmovies.API.Client;
import com.example.movies.popularmovies.API.Services;
import com.example.movies.popularmovies.Adapters.TrailerAdapter;
import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.Model.Review;
import com.example.movies.popularmovies.Model.Trailer;
import com.example.movies.popularmovies.Model.TrailerReply;
import com.example.movies.popularmovies.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerFragment  extends Fragment  {
    public int movieID;
    private Context context;
    TrailerAdapter adapter;
    RecyclerView recyclerView;
    List<Trailer> trailers;

    private static final String API_KEY = "bf3311f677001ebb53bbbeffd6ac9a32";
    public static final String ARG_MOVIEID = "movieID";
    public static final String TAG = TrailerFragment.class.getName();

    public TrailerFragment() {

    }

    public static TrailerFragment newInstance(int movieID) {
        TrailerFragment fragment = new TrailerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MOVIEID,movieID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieID = getArguments().getInt(ARG_MOVIEID);
            getTrailers(movieID);
        }

        Log.d(TAG,movieID+"");
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_movie_trailer,container,false);
      recyclerView = view.findViewById(R.id.trailersRecyclerView);
      return view;



    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public List<Trailer> getTrailers(int movieID) {
        Client client = new Client();
        Services services = client.getClient().create(Services.class);
        trailers = new ArrayList<>();
        Call<TrailerReply> trailerCall;
        trailerCall = services.getTrailer(movieID,API_KEY);
        trailerCall.enqueue(new Callback<TrailerReply>() {
            @Override
            public void onResponse(Call<TrailerReply> call, Response<TrailerReply> response) {
                List<Trailer> trailers = response.body().getResults();
                adapter = new TrailerAdapter(trailers,getActivity().getApplicationContext());

                Log.d(TAG,trailers.toString()+"");

                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());

                recyclerView.setLayoutManager(manager);

                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL));

                recyclerView.setItemAnimator(new DefaultItemAnimator());

                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }
                @Override
            public void onFailure(Call<TrailerReply> call, Throwable t) {
                Toast.makeText(context,"This Movie doesnt have trailers",Toast.LENGTH_LONG).show();

            }
        });
        return trailers;

}


}
