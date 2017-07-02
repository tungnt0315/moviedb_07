package com.example.tung.moviedb_07.data.source;

import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by tung on 6/13/17.
 */

public interface MovieDataSource {

    /**
     * LocalData For Movie
     */
    interface LocalDataSource {
        Observable<Boolean> addMovie(Movie movie);

        Observable<Boolean> deleteMovie(Movie movie);

        Observable<List<Movie>> getMovies(int page);

        Observable<MovieList> getMovieList();

        Observable<Boolean> isFavoriteMovie(Movie movie);
    }

    /**
     * RemoteData For Movie
     */
    interface RemoteDataSource {

        Observable<Movie> getMovieDetails(int movieId);

        Observable<List<Genre>> getGenres();

        Observable<MovieList> getMovieList(int tab, Object objSearch, String sortBy);

        Observable<List<Movie>> getMovies(int tab, Object objSearch, String sortBy, int page);
    }
}
