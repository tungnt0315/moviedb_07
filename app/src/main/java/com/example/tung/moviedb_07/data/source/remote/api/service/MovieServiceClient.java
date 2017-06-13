package com.example.tung.moviedb_07.data.source.remote.api.service;

import android.app.Application;
import android.support.annotation.NonNull;
import com.example.tung.moviedb_07.utils.Constant;

/**
 * Created by tung on 6/13/17.
 */

public class MovieServiceClient extends ServiceClient {
    private static MovieApi mInstance;

    public static void initialize(@NonNull Application application) {
        mInstance = createService(application, Constant.API_URL, MovieApi.class);
    }

    public static MovieApi getInstance() {
        if (mInstance == null) {
            throw new RuntimeException("Need call method MovieServiceClient#initialize() first");
        }
        return mInstance;
    }
}
