package com.ilesanmi.oluwole.popular_movies_app.service;

import android.content.Context;
import android.os.AsyncTask;

import com.ilesanmi.oluwole.popular_movies_app.data.DbInsert;
import com.ilesanmi.oluwole.popular_movies_app.util.MoviesFlagUtil;
import com.ilesanmi.oluwole.popular_movies_app.util.NetworkUtil;


import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by abayomi on 31/01/2018.
 */

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    Context mContext;
    private DbInsert mDbInsert = new DbInsert(mContext);
    private String jsonReturnedForPopularMovies = "";
    private String jsonReturnedForTopRatedMovies = "";
    private final WeakReference<Context> contextReference;


    //WeakReference class is used to prevent potential memory leaks
    public MyAsyncTask(Context context) {
        this.contextReference = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {

            try {
                jsonReturnedForPopularMovies = NetworkUtil.getResponseFromHttpUrl(NetworkUtil.buildPopularMoviesUrl());
                jsonReturnedForTopRatedMovies = NetworkUtil.getResponseFromHttpUrl(NetworkUtil.buildTopRatedMoviesUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mDbInsert.insertIntoDatabase(jsonReturnedForPopularMovies, MoviesFlagUtil.flagForPopularMovies);
                mDbInsert.insertIntoDatabase(jsonReturnedForTopRatedMovies, MoviesFlagUtil.flagForTopRatedMovies);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return null;
        }
}