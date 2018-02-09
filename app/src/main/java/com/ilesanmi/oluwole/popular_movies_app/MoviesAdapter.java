package com.ilesanmi.oluwole.popular_movies_app;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by abayomi on 01/02/2018.
 */

public class MoviesAdapter extends BaseAdapter {
    private final Context context;
    private final Cursor cursor;

    public MoviesAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        cursor.moveToPosition(position);
        return cursor.getLong(cursor.getColumnIndex("_id"));

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        cursor.moveToPosition(position);
        String imageID = cursor.getString(cursor.getColumnIndex("movie_poster"));
        Log.v("Test", imageID);

        if (convertView == null) {
            // If convertView is null then inflate the appropriate layout file
            convertView = LayoutInflater.from(context).inflate(R.layout.items_image, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.place_image);
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w500/"+imageID)
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerCrop()
                .into(imageView);

        return convertView;
    }
}