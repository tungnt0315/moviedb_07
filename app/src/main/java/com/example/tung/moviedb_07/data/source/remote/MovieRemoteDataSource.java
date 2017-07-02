package com.example.tung.moviedb_07.data.source.remote;

import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.GenreList;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
import com.example.tung.moviedb_07.data.source.MovieDataSource;
import com.example.tung.moviedb_07.data.source.remote.api.service.MovieApi;
import com.example.tung.moviedb_07.utils.Constant;
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
    public Observable<Movie> getMovieDetails(int movieId) {
        return mMovieApi.getMovieDetails(movieId);
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        return mMovieApi.getGenres()
                .flatMap(new Function<GenreList, ObservableSource<List<Genre>>>() {
                    @Override
                    public ObservableSource<List<Genre>> apply(GenreList genreList)
                            throws Exception {
                        return Observable.just(genreList.getGenres());
                    }
                });
    }

    @Override
    public Observable<List<Movie>> getMovies(int tab, Object objSearch, String sortBy, int page) {
        return getObservableMovieList(tab, objSearch, sortBy, page).flatMap(
                new Function<MovieList, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(MovieList movieList)
                            throws Exception {
                        return Observable.just(movieList.getMovies());
                    }
                });
    }

    @Override
    public Observable<MovieList> getMovieList(int tab, Object objSearch, String sortBy) {
        return getObservableMovieList(tab, objSearch, sortBy, 1);
    }

    private Observable<MovieList> getObservableMovieList(int tab, Object objSearch, String sortBy,
            int page) {
        Observable<MovieList> observable;
        switch (tab) {
            case Constant.TAB_POPULAR:
                observable = mMovieApi.getPopularMovies(page);
                break;
            case Constant.TAB_NOW_PLAYING:
                observable = mMovieApi.getNowPlayingMovies(page);
                break;
            case Constant.TAB_UPCOMING:
                observable = mMovieApi.getUpcomingMovies(page);
                break;
            case Constant.TAB_TOP_RATED:
                observable = mMovieApi.getTopRatedMovies(page);
                break;
            case Constant.TAB_SEARCH_BY_NAME:
                observable = mMovieApi.searchMoviesByName((String) objSearch, page);
                break;
            case Constant.TAB_SEARCH_BY_GENRE:
                observable = mMovieApi.searchMoviesByGenre((int) objSearch, sortBy, page);
                break;
            case Constant.TAB_SEARCH_BY_CAST:
                observable = mMovieApi.searchMoviesByCast((int) objSearch, sortBy, page);
                break;
            default:    // TAB_SEARCH_BY_CREW
                observable = mMovieApi.searchMoviesByCrew((int) objSearch, sortBy, page);
        }
        return observable;
    }
}
