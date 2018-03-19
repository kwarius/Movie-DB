package com.ruslan.moviedb.moviedb.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static final String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE = "w185";
    public static final String BACKDROP_SIZE = "w780";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
