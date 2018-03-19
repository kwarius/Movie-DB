package com.ruslan.moviedb.moviedb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.ruslan.moviedb.moviedb.R;
import com.ruslan.moviedb.moviedb.model.Movie;

import com.ruslan.moviedb.moviedb.rest.ApiClient;
import com.squareup.picasso.Picasso;



public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieViewHolder> {

    private Movie movie;
    private List<Movie> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        ImageView backdropView;
        ImageView imageView;
        TextView movieTitle;
        TextView data;
        TextView movieGenre;
        TextView rating;
        TextView runtime;
        TextView overview;

        public MovieViewHolder(View v) {
            super(v);

            moviesLayout = (LinearLayout) v.findViewById(R.id.movie_layout);
            backdropView = (ImageView) v.findViewById(R.id.movie_back_drop_path);
            imageView = (ImageView) v.findViewById(R.id.movie_cover);
            movieTitle = (TextView) v.findViewById(R.id.movie_title_details);
            movieGenre = (TextView) v.findViewById(R.id.movie_genre);
            rating = (TextView) v.findViewById(R.id.movie_rating);
            runtime = (TextView) v.findViewById(R.id.movie_runtime);
            data = (TextView) v.findViewById(R.id.movie_release_date);
            overview = (TextView) v.findViewById(R.id.movie_overview);

        }
    }

    public MovieDetailAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieDetailAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new MovieViewHolder(view);
    }

    private String createBackdropLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ApiClient.BASE_IMAGES_URL);
        stringBuilder.append(ApiClient.BACKDROP_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    private String createPosterLink(String path) {
        if (path == null) return null;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ApiClient.BASE_IMAGES_URL);
        stringBuilder.append(ApiClient.POSTER_SIZE);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        holder.movieTitle.setText(movies.get(position).getTitle());
        //holder.movieGenre.setText((CharSequence) movies.get(position).getGenreIds());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.runtime.setText(movies.get(position).getReleaseDate());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.overview.setText(movies.get(position).getOverview());

        Picasso.with(holder.imageView.getContext()).load(movie.getPosterPath()).into(holder.imageView);
        Picasso.with(holder.backdropView.getContext()).load(movie.getBackdropPath()).into(holder.backdropView);

    }

    @Override
    public int getItemCount() {

        return 1;
    }
}
