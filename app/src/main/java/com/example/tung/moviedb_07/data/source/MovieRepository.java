package com.example.tung.moviedb_07.data.source;

import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.utils.Constant;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by tung on 6/13/17.
 */

public class MovieRepository {
    private MovieDataSource.LocalDataSource mLocalDataSource;
    private MovieDataSource.RemoteDataSource mRemoteDataSource;

    public MovieRepository(MovieDataSource.LocalDataSource localDataSource,
            MovieDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Movie>> getPopularMovies(String apiKey, int page) {
        return mRemoteDataSource.getPopularMovies(Constant.API_KEY, page);
    }

    public Observable<List<Movie>> getNowPlayingMovies(String apiKey, int page) {
        return mRemoteDataSource.getNowPlayingMovies(Constant.API_KEY, page);
    }

    public Observable<List<Movie>> getUpcomingMovies(String apiKey, int page) {
        return mRemoteDataSource.getUpcomingMovies(Constant.API_KEY, page);
    }

    public Observable<List<Movie>> getTopRatedMovies(String apiKey, int page) {
        return mRemoteDataSource.getTopRatedMovies(Constant.API_KEY, page);
    }

    public Observable<Movie> getMovieDetails(int movieId, String apiKey) {
        return mRemoteDataSource.getMovieDetails(movieId, apiKey);
    }

    public Observable<List<Genre>> getGenres(String apiKey) {
        return mRemoteDataSource.getGenres(Constant.API_KEY);
    }

    public Observable<List<Movie>> searchMoviesByName(String apiKey, String query, int page) {
        return mRemoteDataSource.searchMoviesByName(Constant.API_KEY, query, page);
    }

    public Observable<List<Movie>> searchMoviesByGenre(String apiKey, int genreId, int page) {
        return mRemoteDataSource.searchMoviesByGenre(Constant.API_KEY, genreId, page);
    }

    public Observable<List<Movie>> searchMoviesByCast(String apiKey, int castId, int page) {
        return mRemoteDataSource.searchMoviesByCast(Constant.API_KEY, castId, page);
    }

    public Observable<Boolean> addMovie(Movie movie) {
        return mLocalDataSource.addMovie(movie);
    }

    public Observable<Boolean> deleteMovie(Movie movie) {
        return mLocalDataSource.deleteMovie(movie);
    }

    public Observable<List<Movie>> getFavoriteMovies(int page) {
        return mLocalDataSource.getMovies(page);
    }

    public Observable<Boolean> isFavoriteMovie(Movie movie) {
        return mLocalDataSource.isFavoriteMovie(movie);
    }
}
