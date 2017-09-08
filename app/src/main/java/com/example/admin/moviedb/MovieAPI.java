package com.example.admin.moviedb;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.admin.moviedb.model.MovieResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Admin on 9/8/2017.
 */

public class MovieAPI extends Thread{



    public static final String TAG = "MovieAPI";
    public static final String API_KEY = "dcca817b074c70ccd1efcc41cabc6dce";

    OkHttpClient client = new OkHttpClient();
    Handler handler = new Handler(Looper.getMainLooper());

    ActivityView view;

    MovieAPI(ActivityView view){
        this.view = view;
    }

    //https://api.themoviedb.org/3/search/movie?query=matrix&api_key=dcca817b074c70ccd1efcc41cabc6dce&language=en-US&page=1&include_adult=false

    public void GetMovieList(String keyword, int page){
        if(!(keyword == null)) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("api.themoviedb.org")
                    .addPathSegment("3")
                    .addPathSegment("search")
                    .addPathSegment("movie")
                    .addQueryParameter("query", keyword)
                    .addQueryParameter("api_key", API_KEY)
                    .addQueryParameter("language", "en-US")
                    .addQueryParameter("page", "1")
                    .addQueryParameter("include_adult", "false")
                    .build();

            Log.d(TAG, "GetMovieList: " + url);

            final Request request = new Request.Builder().url(url).build();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String result;
                        result = client.newCall(request).execute().body().string();
                        Gson gson = new Gson();
                        final MovieResponse foo = gson.fromJson(result, MovieResponse.class);
                        Log.d(TAG, "run: " + result);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.ParseMovieResults(foo);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

}
