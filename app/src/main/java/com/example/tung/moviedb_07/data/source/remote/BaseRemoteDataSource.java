package com.example.tung.moviedb_07.data.source.remote;

import com.example.tung.moviedb_07.data.source.remote.api.service.MovieApi;

/**
 * Created by tung on 6/13/17.
 */

public abstract class BaseRemoteDataSource {

    MovieApi mMovieApi;

    public BaseRemoteDataSource(MovieApi movieApi) {
        mMovieApi = movieApi;
    }
}
