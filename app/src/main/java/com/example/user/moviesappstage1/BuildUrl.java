package com.example.user.moviesappstage1;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class BuildUrl {

    private static final String BASE_URI="https://api.themoviedb.org";
    private static final String VALUE="3";
    private static final String NAME="movie";
    private static final String API_KEY="api_key";
    private static final String API_VALUE="19d6e8afa81597feec6ea0a33635a2d1";

    static URL buildMovieUrl(String item)
    {
        Uri uri=Uri.parse(BASE_URI).buildUpon().
                appendPath(VALUE)
                .appendPath(NAME)
                .appendPath(item)
                .appendQueryParameter(API_KEY,API_VALUE).build();
        URL url=null;
        try
        {
            url=new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }
    static String buildImage(String select)
    {
        return "http://image.tmdb.org/t/p/w342"+select;
    }

}

//https://api.themoviedb.org/3/movie/type=popular or top_rated/19d6e8afa81597feec6ea0a33635a2d1
