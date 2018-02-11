package com.ilesanmi.oluwole.popularmoviesapp.data;

import android.content.ContentValues;
import android.content.Context;


import com.ilesanmi.oluwole.popularmoviesapp.MyParcelable;
import com.ilesanmi.oluwole.popularmoviesapp.util.JsonUtil;


import org.json.JSONException;

/**
 * Created by abayomi on 01/02/2018.
 */

public class DbInsert {


    private static final int numberOfJsonObjInMoviesdbJson = 20;

    public static void insertIntoDatabase(String str,int databaseMoviesFlagToSeparateTopRatedFromPopular,Context context) throws JSONException {

        for(int i = 0; i < numberOfJsonObjInMoviesdbJson; i++){
            MyParcelable myParcelable =  JsonUtil.parseMoviesToParcelableObject(str, i,databaseMoviesFlagToSeparateTopRatedFromPopular);


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
