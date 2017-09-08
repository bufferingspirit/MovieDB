package com.example.admin.moviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.moviedb.model.Result;

public class MovieActivity extends AppCompatActivity {

    TextView tvName, tvVoteAverage, tvDescription;
    ImageView ivPoster;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        tvName = (TextView) findViewById(R.id.tvName);
        tvVoteAverage = (TextView) findViewById(R.id.tvVoteAverage);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivPoster = (ImageView) findViewById(R.id.ivPoster);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result = (Result) bundle.getSerializable("movie");

        tvName.setText(result.getOriginalTitle());
        tvVoteAverage.setText("Score: " + Float.toString(result.getVoteAverage()));
        tvDescription.setText("Overview: " + result.getOverview());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(ivPoster);

    }
}
