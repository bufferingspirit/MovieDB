package com.example.admin.moviedb;

import com.example.admin.moviedb.model.MovieResponse;

/**
 * Created by Admin on 9/8/2017.
 */

public interface ActivityView {
    void ParseMovieResults(MovieResponse r);
    void ParseSearchChange(String s);
}
