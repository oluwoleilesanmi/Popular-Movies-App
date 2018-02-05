package com.ilesanmi.oluwole.popular_movies_app.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by abayomi on 29/01/2018.
 */

public class MoviesContract {

    public static final String AUTHORITY = "com.ilesanmi.oluwole.popular_movies_app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIES = "movies";


    public static final class MoviesEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "movies";


        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_MOVIE_POSTER = "movie_poster";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_PLOT_SYNOPSIS = "plot_synopsis";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIES_FLAG = "movies_flag";

    }
}
