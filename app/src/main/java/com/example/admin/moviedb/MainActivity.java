package com.example.admin.moviedb;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.moviedb.model.MovieResponse;
import com.example.admin.moviedb.model.Result;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActivityView {

    public static final String TAG = "MainActivity";
    MovieAPI movieAPI;

    int page;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;

    EditText etMovie;
    ImageView btnSearch;
    MovieListAdapter movieListAdapter;
    ArrayList<Result> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAPI = new MovieAPI(this);

        recyclerView = (RecyclerView) findViewById(R.id.recMovieView);
        itemAnimator = new DefaultItemAnimator();
        layoutManager = new GridLayoutManager(this,4);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setLayoutManager(layoutManager);

        resultList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(resultList);
        recyclerView.setAdapter(movieListAdapter);

        etMovie = (EditText) findViewById(R.id.etSearch);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);
    }

    @Override
    public void ParseMovieResults(MovieResponse r) {
        Log.d(TAG, "ParseMovieResults: " + r.getTotalResults());
        if(r.getResults() != null) {
            resultList.clear();
            resultList.addAll(r.getResults());
            movieListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ParseSearchChange(String s) {

    }

    public void openSearchDialogue(View view){
        etMovie.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.VISIBLE);

    }

    public void doSearch(View view){
        movieAPI.GetMovieList(etMovie.getText().toString(), page);
    }
}
