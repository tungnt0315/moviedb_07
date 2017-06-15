package com.example.tung.moviedb_07.data.source;

import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
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
        Observable<Void> addMovie(Movie movie);

        Observable<Void> deleteMovie(Movie movie);

        Observable<List<Movie>> getMovies(int page);

        Observable<Boolean> isFavoriteMovie(Movie movie);
    }

    /**
     * RemoteData For Movie
     */
    interface RemoteDataSource {
        Observable<List<Movie>> getPopularMovies(String apiKey, int page);

        Observable<List<Movie>> getNowPlayingMovies(String apiKey, int page);

        Observable<List<Movie>> getUpcomingMovies(String apiKey, int page);

        Observable<List<Movie>> getTopRatedMovies(String apiKey, int page);

        Observable<Movie> getMovieDetails(int movieId, String apiKey);

        Observable<List<Genre>> getGenres(String apiKey);

        Observable<List<Movie>> searchMoviesByName(String apiKey, String query, int page);

        Observable<List<Movie>> searchMoviesByGenre(String apiKey, int genreId, int page);

        Observable<List<Movie>> searchMoviesByCast(String apiKey, int castId, int page);
    }
}
