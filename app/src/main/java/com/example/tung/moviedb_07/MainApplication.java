package com.example.tung.moviedb_07;

import android.app.Application;
import com.example.tung.moviedb_07.data.source.remote.api.service.MovieServiceClient;

/**
 * Created by tung on 6/14/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MovieServiceClient.initialize(this);
    }
}
