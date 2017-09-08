package com.example.admin.moviedb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import okhttp3.OkHttpClient;

/**
 * Created by Admin on 9/8/2017.
 */

public class MovieAPI extends Thread{

    //https://maps.googleapis.com/maps/api/place/autocomplete/json?input=ban&key=API_KEY
    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY

    public static final String TAG = "PlacesAPI";
    public static final String API_KEY = "AIzaSyArobT3WQlXm9fEf3zrEiZzghpQGQifeQ0";

    OkHttpClient client = new OkHttpClient();
    Handler handler = new Handler(Looper.getMainLooper());

    MainActivityView view;

    MovieAPI(MainActivityView view){
        this.view = view;
    }

    

}
