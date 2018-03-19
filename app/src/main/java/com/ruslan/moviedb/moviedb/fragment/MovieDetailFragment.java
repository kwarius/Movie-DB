package com.ruslan.moviedb.moviedb.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruslan.moviedb.moviedb.model.Movie;
import com.ruslan.moviedb.moviedb.model.MoviesResponse;
import com.ruslan.moviedb.moviedb.adapter.MovieDetailAdapter;
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

public class MovieDetailFragment extends Fragment {

    private static final String TAG = "MovieDetailFragment";
    private final static String API_KEY = "0aef204d05c09562f5ae90f356c9a2de";
    private Movie movie;
    private int movieId;

    public MovieDetailFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movie_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            movieId = bundle.getInt("id");
        }

        Call<MoviesResponse> call = apiService.getMovieDetails(movieId, API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();


                int statusCode = response.code();
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(new MovieDetailAdapter(movies, R.layout.item_movie_detail, getActivity().getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        return rootView;
    }

//    public interface OnFragmentInteractionListener{
//        public void onFragmentInteraction(Uri uri);
//    }
}
