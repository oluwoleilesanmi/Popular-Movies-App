package com.ilesanmi.oluwole.popularmoviesapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.ilesanmi.oluwole.popularmoviesapp.data.DbInsert;
import com.ilesanmi.oluwole.popularmoviesapp.util.MovieTypeConstants;
import com.ilesanmi.oluwole.popularmoviesapp.util.NetworkUtil;


import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * Created by abayomi on 01/02/2018.
 */

public class MoviesAsyncTask extends AsyncTask<Void, Void, Void> {
    private String jsonReturnedForPopularMovies = "";
    private String jsonReturnedForTopRatedMovies = "";
    private final WeakReference<Context> contextReference ;


    //WeakReference class is used to prevent potential memory leaks
    public MoviesAsyncTask(Context context) {
        contextReference = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {

            try {
                jsonReturnedForPopularMovies = NetworkUtil.getStringFromRemoteServer(NetworkUtil.buildPopularMoviesUrl());
                jsonReturnedForTopRatedMovies = NetworkUtil.getStringFromRemoteServer(NetworkUtil.buildTopRatedMoviesUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //if internet query returned nothing then do not update the database
                if(!Objects.equals(jsonReturnedForPopularMovies, "") && !Objects.equals(jsonReturnedForTopRatedMovies, "")) {
                    DbInsert.insertIntoDatabase(jsonReturnedForPopularMovies, MovieTypeConstants.flagForPopularMovies,this.contextReference.get());
                    DbInsert.insertIntoDatabase(jsonReturnedForTopRatedMovies, MovieTypeConstants.flagForTopRatedMovies,this.contextReference.get());
                }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
        return null;
        }
}