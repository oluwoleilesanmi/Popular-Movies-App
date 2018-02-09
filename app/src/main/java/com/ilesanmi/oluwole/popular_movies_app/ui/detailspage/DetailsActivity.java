package com.ilesanmi.oluwole.popular_movies_app.ui.detailspage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilesanmi.oluwole.popular_movies_app.MyParcelable;
import com.ilesanmi.oluwole.popular_movies_app.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private TextView mVoteAverage;
    private TextView mPlotSynopsis;
    private TextView mReleaseDate;
    private Bundle dataBundle;
    private Bundle osIntentBundle;
    private MyParcelable parcelableFromMainActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mVoteAverage = findViewById(R.id.vote_average_tv);
        mPlotSynopsis = findViewById(R.id.plot_synopsis_tv);
        mReleaseDate = findViewById(R.id.release_date_tv);

        ImageView imageView = findViewById(R.id.image_iv);


        Intent intentFromMoviesActivity = getIntent();
        if (intentFromMoviesActivity == null) {
            closeOnError();
        }

        osIntentBundle = intentFromMoviesActivity.getExtras();
        parcelableFromMainActivity = osIntentBundle.getParcelable("parcelableToSend");
        dataBundle = parcelableFromMainActivity.getBundle();




        String imageID = dataBundle.getString("movie_poster");
        populateUI();
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/"+imageID)
                .fit()
                .centerCrop()
                .into(imageView);


        setTitle(dataBundle.getString("title"));

    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
      mPlotSynopsis.setText(dataBundle.getString("plot_synopsis"));
      mReleaseDate.setText(dataBundle.getString("release_date")) ;
      mVoteAverage.setText(Double.toString(dataBundle.getDouble("vote_average")));

    }

}