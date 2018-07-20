package com.ilesanmi.oluwole.popularmoviesapp;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abayomi on 1/02/2018.
 */

public class MoviesParcelable implements Parcelable {

    private String title;
    private String posterPath;
    private Double voteAverage;
    private String overview;
    private String releaseDate;
    private Integer flag;
    private Bundle bundle = new Bundle();

    public MoviesParcelable(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Bundle getBundle(){return bundle;}



    private MoviesParcelable(Parcel in) {
        bundle = in.readBundle(getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {dest.writeBundle(bundle);}

    public static final Creator<MoviesParcelable> CREATOR = new Creator<MoviesParcelable>() {
        @Override
        public MoviesParcelable createFromParcel(Parcel in) {
            return new MoviesParcelable(in);
        }

        @Override
        public MoviesParcelable[] newArray(int size) {
            return new MoviesParcelable[size];
        }
    };
    public void convertCursor(Cursor cursor){
        //cursor starts from position -1 move it by one position
        cursor.moveToNext();

        bundle.putString("title",cursor.getString(cursor.getColumnIndex("title")));
        bundle.putString("movie_poster",cursor.getString(cursor.getColumnIndex("movie_poster")));
        bundle.putDouble("vote_average",cursor.getDouble(cursor.getColumnIndex("vote_average")));
        bundle.putString("plot_synopsis",cursor.getString(cursor.getColumnIndex("plot_synopsis")));
        bundle.putString("release_date",cursor.getString(cursor.getColumnIndex("release_date")));
    }
}


