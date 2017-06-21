package com.example.tung.moviedb_07.data.source;

import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
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

    public Observable<List<Movie>> getMovies(int tab, Object objSearch, int page) {
        return mRemoteDataSource.getMovies(tab, objSearch, page);
    }

    public Observable<MovieList> getMovieList(int tab, Object objSearch) {
        return mRemoteDataSource.getMovieList(tab, objSearch);
    }

    public Observable<Movie> getMovieDetails(int movieId) {
        return mRemoteDataSource.getMovieDetails(movieId);
    }

    public Observable<List<Genre>> getGenres() {
        return mRemoteDataSource.getGenres();
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

    public Observable<MovieList> getFavoriteList() {
        return mLocalDataSource.getMovieList();
    }
}
