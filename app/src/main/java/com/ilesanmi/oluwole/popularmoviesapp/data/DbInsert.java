package com.ilesanmi.oluwole.popularmoviesapp.data;

import android.content.ContentValues;
import android.content.Context;


import com.ilesanmi.oluwole.popularmoviesapp.MoviesParcelable;
import com.ilesanmi.oluwole.popularmoviesapp.util.JsonUtil;


import org.json.JSONException;

/**
 * Created by abayomi on 01/02/2018.
 */

public class DbInsert {


    private static final int numberOfJsonObjInMoviesdbJson = 20;

    public static void insertIntoDatabase(String str,int databaseMoviesFlagToSeparateTopRatedFromPopular,Context context) throws JSONException {

        for(int i = 0; i < numberOfJsonObjInMoviesdbJson; i++){
            MoviesParcelable moviesParcelable =  JsonUtil.parseMoviesToParcelableObject(str, i,databaseMoviesFlagToSeparateTopRatedFromPopular);


            ContentValues contentValues = new ContentValues();

            contentValues.put(MoviesContract.MoviesEntry.COLUMN_TITLE, moviesParcelable.getTitle());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_MOVIE_POSTER, moviesParcelable.getPosterPath());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE, moviesParcelable.getVoteAverage());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_PLOT_SYNOPSIS, moviesParcelable.getOverview());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE, moviesParcelable.getReleaseDate());
            contentValues.put(MoviesContract.MoviesEntry.COLUMN_MOVIES_FLAG,  moviesParcelable.getFlag());

            context.getContentResolver().insert(MoviesContract.MoviesEntry.CONTENT_URI,contentValues);        }

    }

}
