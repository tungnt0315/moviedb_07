package com.example.tung.moviedb_07.data.source.remote.api.service;

import com.example.tung.moviedb_07.data.model.GenreList;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tung on 6/13/17.
 */

public interface MovieApi {

    String API_KEY_PATH = "api_key=08a61380bb9b09c429ae72d1ae550d5c";
    String PAGE_QUERY = "page";

    @GET("movie/popular?" + API_KEY_PATH)
    Observable<MovieList> getPopularMovies(@Query(PAGE_QUERY) int page);

    @GET("movie/now_playing?" + API_KEY_PATH)
    Observable<MovieList> getNowPlayingMovies(@Query(PAGE_QUERY) int page);

    @GET("movie/upcoming?" + API_KEY_PATH)
    Observable<MovieList> getUpcomingMovies(@Query(PAGE_QUERY) int page);

    @GET("movie/top_rated?" + API_KEY_PATH)
    Observable<MovieList> getTopRatedMovies(@Query(PAGE_QUERY) int page);

    @GET("movie/{movie_id}?append_to_response=videos,credits&" + API_KEY_PATH)
    Observable<Movie> getMovieDetails(@Path("movie_id") int movieId);

    @GET("genre/movie/list?" + API_KEY_PATH)
    Observable<GenreList> getGenres();

    @GET("search/movie?" + API_KEY_PATH)
    Observable<MovieList> searchMoviesByName(@Query("query") String query,
            @Query(PAGE_QUERY) int page);

    @GET("discover/movie?sort_by=popularity.desc&" + API_KEY_PATH)
    Observable<MovieList> searchMoviesByGenre(@Query("with_genres") int genreId,
            @Query("sort_by") String sortBy, @Query(PAGE_QUERY) int page);

    @GET("discover/movie?" + API_KEY_PATH)
    Observable<MovieList> searchMoviesByCast(@Query("with_cast") int castId,
            @Query("sort_by") String sortBy, @Query(PAGE_QUERY) int page);

    @GET("discover/movie?" + API_KEY_PATH)
    Observable<MovieList> searchMoviesByCrew(@Query("with_crew") int crewId,
            @Query("sort_by") String sortBy, @Query(PAGE_QUERY) int page);
}
