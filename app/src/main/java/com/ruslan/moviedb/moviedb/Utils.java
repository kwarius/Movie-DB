package com.ruslan.moviedb.moviedb;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ruslan.moviedb.moviedb.model.Genre;


/**
 * Utility class that provides some useful methods for the app
 */

public class Utils {
    /**
     * Create a genres string from an array
     * @param genres
     * @return
     */
    public static String createGenreText(Genre[] genres) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < genres.length; i++) {
            stringbuilder.append(genres[i].getName());
            if (i < genres.length - 1) {
                stringbuilder.append(", ");
            }
        }
        return stringbuilder.toString();
    }



}
