package com.example.tung.moviedb_07.data.source.remote.api.service;

import com.example.tung.moviedb_07.data.model.GenreList;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.tung.moviedb_07.utils.Constant.API_KEY_QUERY;
import static com.example.tung.moviedb_07.utils.Constant.PAGE_QUERY;

/**
 * Created by tung on 6/13/17.
 */

public interface MovieApi {

    @GET("movie/popular")
    Observable<MovieList> getPopularMovies(@Query(API_KEY_QUERY) String apiKey, @Query(PAGE_QUERY) int page);

    @GET("movie/now_playing")
    Observable<MovieList> getNowPlayingMovies(@Query(API_KEY_QUERY) String apiKey, @Query(PAGE_QUERY) int page);

    @GET("movie/upcoming")
    Observable<MovieList> getUpcomingMovies(@Query(API_KEY_QUERY) String apiKey, @Query(PAGE_QUERY) int page);

    @GET("movie/top_rated")
    Observable<MovieList> getTopRatedMovies(@Query(API_KEY_QUERY) String apiKey, @Query(PAGE_QUERY) int page);

    @GET("movie/{movie_id}?append_to_response=videos,credits")
    Observable<Movie> getMovieDetails(@Path("movie_id") int movieId, @Query(API_KEY_QUERY) String apiKey);

    @GET("genre/movie/list")
    Observable<GenreList> getGenres(@Query(API_KEY_QUERY) String apiKey);

    @GET("search/movie")
    Observable<MovieList> searchMoviesByName(@Query(API_KEY_QUERY) String apiKey,
            @Query("query") String query, @Query(PAGE_QUERY) int page);
}
