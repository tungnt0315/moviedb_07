package com.example.tung.moviedb_07.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by tung on 6/13/17.
 */

public class MovieServiceClient extends ServiceClient {
    private static MovieApi mInstance;
    private static final String API_URL = "https://api.themoviedb.org/3/";

    public static void initialize(@NonNull Application application) {
        mInstance = createService(application, API_URL, MovieApi.class);
    }

    public static MovieApi getInstance() {
        if (mInstance == null) {
            throw new RuntimeException("Need call method MovieServiceClient#initialize() first");
        }
        return mInstance;
    }
}
