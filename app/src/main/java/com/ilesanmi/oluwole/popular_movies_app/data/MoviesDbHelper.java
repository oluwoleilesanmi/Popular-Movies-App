package com.ilesanmi.oluwole.popular_movies_app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abayomi on 01/02/2018.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {

        // The name of the database
        private static final String DATABASE_NAME = "moviesDb.db";

        // If you change the database schema, you must increment the database version
        private static final int VERSION = 1;


        // Constructor
        MoviesDbHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }


        /**
         * Called when the tasks database is created for the first time.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create tasks table (careful to follow SQL formatting rules)
            final String CREATE_TABLE = "CREATE TABLE "  + MoviesContract.MoviesEntry.TABLE_NAME + " (" +
                    MoviesContract.MoviesEntry._ID                + " INTEGER PRIMARY KEY, " +
                    MoviesContract.MoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                    MoviesContract.MoviesEntry.COLUMN_MOVIE_POSTER    + " TEXT NOT NULL, " +
                    MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE    + " TEXT NOT NULL, " +
                    MoviesContract.MoviesEntry.COLUMN_PLOT_SYNOPSIS    + " TEXT NOT NULL, " +
                    MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE    + " TEXT NOT NULL, " +
                    MoviesContract.MoviesEntry.COLUMN_MOVIES_FLAG    + " INTEGER NOT NULL);";

            db.execSQL(CREATE_TABLE);
        }


        /**
         * This method discards the old table of data and calls onCreate to recreate a new one.
         * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME);
            onCreate(db);
        }



}
