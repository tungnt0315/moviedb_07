package com.example.tung.moviedb_07.data.source.remote;

import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.GenreList;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
import com.example.tung.moviedb_07.data.source.MovieDataSource;
import com.example.tung.moviedb_07.data.source.remote.api.service.MovieApi;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by tung on 6/13/17.
 */

public class MovieRemoteDataSource extends BaseRemoteDataSource
        implements MovieDataSource.RemoteDataSource {

    public MovieRemoteDataSource(MovieApi movieApi) {
        super(movieApi);
    }

    @Override
    public Observable<List<Movie>> getPopularMovies(String apiKey, int page) {
        return mMovieApi.getPopularMovies(apiKey, page)
                .flatMap(new Function<MovieList, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(MovieList movieList)
                            throws Exception {
                        return Observable.just(movieList.getMovies());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> getNowPlayingMovies(String apiKey, int page) {
        return mMovieApi.getNowPlayingMovies(apiKey, page)
                .flatMap(new Function<MovieList, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(MovieList movieList)
                            throws Exception {
                        return Observable.just(movieList.getMovies());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> getUpcomingMovies(String apiKey, int page) {
        return mMovieApi.getUpcomingMovies(apiKey, page)
                .flatMap(new Function<MovieList, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(MovieList movieList)
                            throws Exception {
                        return Observable.just(movieList.getMovies());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> getTopRatedMovies(String apiKey, int page) {
        return mMovieApi.getTopRatedMovies(apiKey, page)
                .flatMap(new Function<MovieList, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(MovieList movieList)
                            throws Exception {
                        return Observable.just(movieList.getMovies());
                    }
                });
    }

    @Override
    public Observable<Movie> getMovieDetails(int movieId, String apiKey) {
        return mMovieApi.getMovieDetails(movieId, apiKey);
    }

    @Override
    public Observable<List<Genre>> getGenres(String apiKey) {
        return mMovieApi.getGenres(apiKey)
                .flatMap(new Function<GenreList, ObservableSource<List<Genre>>>() {
                    @Override
                    public ObservableSource<List<Genre>> apply(GenreList genreList)
                            throws Exception {
                        return Observable.just(genreList.getGenres());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> searchMoviesByName(String apiKey, String query, int page) {
        return mMovieApi.searchMoviesByName(apiKey, query, page)
                .flatMap(new Function<MovieList, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(MovieList movieList)
                            throws Exception {
                        return Observable.just(movieList.getMovies());
                    }
                });
    }
}
