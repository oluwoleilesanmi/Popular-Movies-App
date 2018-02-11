package com.ilesanmi.oluwole.popularmoviesapp.ui.homepage;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;



import com.ilesanmi.oluwole.popularmoviesapp.MoviesAdapter;
import com.ilesanmi.oluwole.popularmoviesapp.MyParcelable;
import com.ilesanmi.oluwole.popularmoviesapp.R;
import com.ilesanmi.oluwole.popularmoviesapp.data.MoviesContract;
import com.ilesanmi.oluwole.popularmoviesapp.service.MyAsyncTask;
import com.ilesanmi.oluwole.popularmoviesapp.ui.detailspage.DetailsActivity;
import com.ilesanmi.oluwole.popularmoviesapp.util.DbUtil;




public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private GridView mGridView;
    private MoviesAdapter moviesAdapter;
    private final Bundle bundle = new Bundle();
    private final MyParcelable myParcelable = new MyParcelable();

    private static final int POPULAR_IMAGES_LOADER = 1;
    private static final int TOP_RATED_IMAGES_LOADER = 2;
    private static final int USER_SELECTED_MOVIE = 3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mGridView = findViewById(R.id.gridView);

        //is database populated with data form moviesdb
        if (DbUtil.isDbEmpty(this)){

            //is this phone connected to internet
            if(isConnectedToInternet()) {
               MyAsyncTask myAsyncTask = new MyAsyncTask(this);
               myAsyncTask.execute();
            }

        }


        getLoaderManager().initLoader(POPULAR_IMAGES_LOADER, null, this);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                bundle.putLong("clickedMovie_id",id);
                getLoaderManager().initLoader(USER_SELECTED_MOVIE, bundle, MainActivity.this);
            }
        });

    }
    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
        switch (loaderID) {
            case POPULAR_IMAGES_LOADER:
                CursorLoader popularImageCursorLoader = new CursorLoader(
                        this,
                        MoviesContract.MoviesEntry.CONTENT_URI,                          // Table to query
                        new String[]{"_id","movie_poster"},                              // _id is placed because of issues with loader callbacks
                        MoviesContract.MoviesEntry.COLUMN_MOVIES_FLAG + "="+ 1,  // WHERE Movies_flag = 1
                        null,
                        null
                );
                popularImageCursorLoader.registerListener(POPULAR_IMAGES_LOADER, null);
                return popularImageCursorLoader;


                case TOP_RATED_IMAGES_LOADER:
                    CursorLoader topRatedImagesCursorLoader = new CursorLoader(
                        this,
                        MoviesContract.MoviesEntry.CONTENT_URI,                          // Table to query
                        new String[]{"_id","movie_poster"},                              // _id is placed because of issues with loader callbacks
                        MoviesContract.MoviesEntry.COLUMN_MOVIES_FLAG + "="+ 2,  // WHERE Movies_flag = 2
                        null,
                        null
                    );
                    topRatedImagesCursorLoader.registerListener(TOP_RATED_IMAGES_LOADER, null);
                    return topRatedImagesCursorLoader;


                case USER_SELECTED_MOVIE:
                    CursorLoader userSelectedMovieCursorLoader = new CursorLoader(
                        this,
                         MoviesContract.MoviesEntry.CONTENT_URI,                                     // Table to query
                            null,                                                           // Return all columns
                        MoviesContract.MoviesEntry._ID + "="+ args.get("clickedMovie_id"),   // WHERE _id = value passed into bundle args
                        null,
                        null
                    );
                    userSelectedMovieCursorLoader.registerListener(USER_SELECTED_MOVIE, null);
                    return userSelectedMovieCursorLoader;

            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        switch (loader.getId()) {
            case POPULAR_IMAGES_LOADER:
                moviesAdapter = new MoviesAdapter(this,data);
                mGridView.setAdapter(moviesAdapter);
                break;

            case TOP_RATED_IMAGES_LOADER:
                moviesAdapter = new MoviesAdapter(this,data);
                mGridView.setAdapter(moviesAdapter);
                break;

            case USER_SELECTED_MOVIE:
                myParcelable.convertCursor(data);

                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra("parcelableToSend", myParcelable);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if (id == R.id.popular) {
            getLoaderManager().initLoader(POPULAR_IMAGES_LOADER, null, this);
            return true;
        }
        if (id == R.id.top_rated) {
            getLoaderManager().initLoader(TOP_RATED_IMAGES_LOADER, null, this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isConnectedToInternet(){
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }


}

