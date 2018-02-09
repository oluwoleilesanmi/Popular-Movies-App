package com.ilesanmi.oluwole.popular_movies_app.util;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by abayomi on 25/01/2018.
 */

public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    private static final String POPULAR_MOVIES_URL =
            "https://api.themoviedb.org/3/movie/popular";

    private static final String TOP_RATED_MOVIES_URL =
            "https://api.themoviedb.org/3/movie/top_rated";




    private static final String api_key_parameter = "api_key";
    private static final String my_moviesdb_api_key = "";


    private static URL url = null;




    public static URL buildPopularMoviesUrl() throws MalformedURLException{

        Uri builtUri = Uri.parse(POPULAR_MOVIES_URL).buildUpon()
                .appendQueryParameter(api_key_parameter, my_moviesdb_api_key)
                .build();

        url = new URL(builtUri.toString());

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static URL buildTopRatedMoviesUrl() throws MalformedURLException{

        Uri builtUri = Uri.parse(TOP_RATED_MOVIES_URL).buildUpon()
                .appendQueryParameter(api_key_parameter, my_moviesdb_api_key)
                .build();

        url = new URL(builtUri.toString());

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
