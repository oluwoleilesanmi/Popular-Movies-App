package com.ilesanmi.oluwole.popular_movies_app.data;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;


import com.ilesanmi.oluwole.popular_movies_app.MyParcelable;
import com.ilesanmi.oluwole.popular_movies_app.util.JsonUtil;


import org.json.JSONException;

/**
 * Created by abayomi on 31/01/2018.
 */

public class DbInsert {

    private static Context context;
    private static MyParcelable myParcelable;
    private static final int numberOfJsonObjInMoviesdbJson = 20;


    public DbInsert(Context context){
        this.context = context;
    }

    public static void insertIntoDatabase(String str,int databaseMoviesFlagToSeperateTopRatedFromPopular) throws JSONException {

        for(int i = 0; i < numberOfJsonObjInMoviesdbJson; i++){
            myParcelable =  JsonUtil.parseMoviesToParcelableObject(str, i,databaseMoviesFlagToSeperateTopRatedFromPopular);


            ContentValues contentValues = new ContentValues();

            contentValues.put(MoviesContract.MoviesEntry.COLUMN_TITLE, myParcelable.getTitle());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_MOVIE_POSTER, myParcelable.getPosterPath());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE, myParcelable.getVoteAverage());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_PLOT_SYNOPSIS, myParcelable.getOverview());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE, myParcelable.getReleaseDate());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_MOVIES_FLAG,  myParcelable.getFlag());

            context.getContentResolver().insert(MoviesContract.MoviesEntry.CONTENT_URI,contentValues);        }



    }



}
