package com.ruslan.moviedb.moviedb.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruslan.moviedb.moviedb.RecyclerItemClickListener;
import com.ruslan.moviedb.moviedb.model.Movie;
import com.ruslan.moviedb.moviedb.model.MoviesResponse;
import com.ruslan.moviedb.moviedb.adapter.MoviesAdapter;
import com.ruslan.moviedb.moviedb.rest.ApiClient;
import com.ruslan.moviedb.moviedb.rest.ApiInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.ruslan.moviedb.moviedb.R;

public class UpcomingMovies extends Fragment{
    private static final String TAG = "UpcomingMovies";
    private final static String API_KEY = "0aef204d05c09562f5ae90f356c9a2de";

    public UpcomingMovies() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upcoming_movies, container, false);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getUpcoming(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                final List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "List of movies: " + movies);
                int statusCode = response.code();
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getActivity().getApplicationContext()));
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //int movieId = new Intent().getIntExtra("id", 0);
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", movies.get(position).getId());
                                MovieDetailFragment fragmentMovieDetails = new MovieDetailFragment();
                                fragmentMovieDetails.setArguments(bundle);

                                FragmentManager manager = getChildFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activivity_main, fragmentMovieDetails).addToBackStack(null).commit();
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );
            }

            @Override
            public void onFailure(Call<MoviesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        return rootView;
    }
    public interface OnFragmentInteractionListener{
        public void onFragmentInteraction(Uri uri);
    }

}