package com.ilesanmi.oluwole.popular_movies_app.ui.homepage;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.ilesanmi.oluwole.popular_movies_app.R;
import com.ilesanmi.oluwole.popular_movies_app.service.MyAsyncTask;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private static final String str = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* private LoaderCallbacks<GetSyncListDataResult> dataResultLoaderListener
                = new LoaderCallbacks<GetSyncListDataResult>() { ...methods here... };

        private LoaderCallbacks<ErrorResult> errorResultLoaderListener
                = new LoaderCallbacks<ErrorResult>() { ...methods here... };
       */
        //new MyAsyncTask().execute();
        //Log.i(str, dataReturnedFromUrl);

        MyAsyncTask myAsyncTask = new MyAsyncTask(this);

        myAsyncTask.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
