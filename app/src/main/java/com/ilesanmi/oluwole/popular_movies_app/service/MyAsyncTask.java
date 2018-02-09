package com.ilesanmi.oluwole.popular_movies_app.service;

import android.content.Context;
import android.os.AsyncTask;

import com.ilesanmi.oluwole.popular_movies_app.data.DbInsert;
import com.ilesanmi.oluwole.popular_movies_app.util.MoviesFlagUtil;
import com.ilesanmi.oluwole.popular_movies_app.util.NetworkUtil;


import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * Created by abayomi on 01/02/2018.
 */

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    private String jsonReturnedForPopularMovies = "";
    private String jsonReturnedForTopRatedMovies = "";
    private final WeakReference<Context> contextReference ;


    //WeakReference class is used to prevent potential memory leaks
    public MyAsyncTask(Context context) {
        contextReference = new WeakReference<>(context);
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
                //if internet query returned nothing then do not update the database
                if(!Objects.equals(jsonReturnedForPopularMovies, "") && !Objects.equals(jsonReturnedForTopRatedMovies, "")) {
                    DbInsert.insertIntoDatabase(jsonReturnedForPopularMovies, MoviesFlagUtil.flagForPopularMovies,this.contextReference.get());
                    DbInsert.insertIntoDatabase(jsonReturnedForTopRatedMovies, MoviesFlagUtil.flagForTopRatedMovies,this.contextReference.get());
                }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
        return null;
        }
}