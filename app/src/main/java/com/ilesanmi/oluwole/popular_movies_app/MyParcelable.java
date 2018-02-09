package com.ilesanmi.oluwole.popular_movies_app;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abayomi on 1/02/2018.
 */

public class MyParcelable implements Parcelable {

    private String title;
    private String posterPath;
    private Double voteAverage;
    private String overview;
    private String releaseDate;
    private Integer flag;
    private Bundle bundle = new Bundle();

    public MyParcelable(){}

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



    private MyParcelable(Parcel in) {
        bundle = in.readBundle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {dest.writeBundle(bundle);}

    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
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


