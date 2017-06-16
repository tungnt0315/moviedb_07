package com.example.tung.moviedb_07.screen.list_item;

import android.os.Bundle;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.source.MovieRepository;
import com.example.tung.moviedb_07.data.source.local.sqlite.MovieLocalDataSource;
import com.example.tung.moviedb_07.data.source.remote.MovieRemoteDataSource;
import com.example.tung.moviedb_07.data.source.remote.api.service.MovieServiceClient;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.screen.movie_detail.MovieDetailActivity;
import com.example.tung.moviedb_07.screen.search_result.SearchResultActivity;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public class ListItemViewModel extends BaseViewModel
        implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private BaseRecyclerViewAdapter mAdapter;
    private Navigator mNavigator;
    private MovieRepository mMovieRepository;

    public ListItemViewModel(Bundle bundle, Navigator navigator) {
        mMovieRepository = new MovieRepository(new MovieLocalDataSource(navigator.getActivity()),
                new MovieRemoteDataSource(MovieServiceClient.getInstance()));
        int tab = bundle.getInt(Constant.BUNDLE_TAB);
        if (tab == Constant.TAB_GENRE) { // list of genres
            mAdapter = new GenreListAdapter(navigator.getActivity());
            getGenres();
        } else {
            mAdapter = new MovieListAdapter(navigator.getActivity());
            switch (tab) {
                case Constant.TAB_SEARCH_BY_NAME:    // search movies by name
                    searchMoviesByName(bundle.getString(Constant.BUNDLE_SEARCH_KEYWORD));
                    break;
                case Constant.TAB_SEARCH_BY_GENRE:    // search movies by genre
                    searchMoviesByGenre(bundle.getInt(Constant.BUNDLE_GENRE_ID));
                    break;
                case Constant.TAB_SEARCH_BY_CAST:    // search movies by cast
                    searchMoviesByCast(bundle.getInt(Constant.BUNDLE_CAST_ID));
                    break;
                default:    // list of movies by popular, upcoming ...
                    getListMovies(tab);
                    break;
            }
        }
        mAdapter.setClickListener(this);
        mNavigator = navigator;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (mAdapter instanceof GenreListAdapter) {
            if (!(item instanceof Genre)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_SEARCH_BY_GENRE);
            bundle.putInt(Constant.BUNDLE_GENRE_ID, ((Genre) item).getId());
            bundle.putString(Constant.BUNDLE_SEARCH_KEYWORD, ((Genre) item).getName());
            mNavigator.startActivity(SearchResultActivity.class, bundle);
        } else {
            if (!(item instanceof Movie)) {
                return;
            }
            mMovieRepository.getMovieDetails(((Movie) item).getId(), Constant.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Movie>() {
                        @Override
                        public void accept(Movie movie) throws Exception {
                            goMovieDetail(movie);
                        }
                    });
        }
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    private void getGenres() {
        Disposable disposable = mMovieRepository.getGenres(Constant.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Genre>>() {
                    @Override
                    public void accept(List<Genre> genres) throws Exception {
                        if (mAdapter instanceof GenreListAdapter) {
                            ((GenreListAdapter) mAdapter).updateData(genres);
                        }
                    }
                });
        startDisposable(disposable);
    }

    private void getListMovies(int tab) {
        Observable<List<Movie>> observable;
        switch (tab) {
            case Constant.TAB_POPULAR:
                // 1 is page number, it will be replaced when i use onLoadMore for recyclerview
                observable = mMovieRepository.getPopularMovies(Constant.API_KEY, 1);
                break;
            case Constant.TAB_NOW_PLAYING:
                observable = mMovieRepository.getNowPlayingMovies(Constant.API_KEY, 1);
                break;
            case Constant.TAB_UPCOMING:
                observable = mMovieRepository.getUpcomingMovies(Constant.API_KEY, 1);
                break;
            case Constant.TAB_TOP_RATED:
                observable = mMovieRepository.getTopRatedMovies(Constant.API_KEY, 1);
                break;
            default: // TAB_FAVORITE
                observable = mMovieRepository.getFavoriteMovies(1);
                break;
        }
        setMovieListAdapter(observable);
    }

    private void searchMoviesByName(String searchKeyword) {
        Observable observable =
                mMovieRepository.searchMoviesByName(Constant.API_KEY, searchKeyword, 1);
        setMovieListAdapter(observable);
    }

    private void searchMoviesByGenre(int genreId) {
        Observable observable = mMovieRepository.searchMoviesByGenre(Constant.API_KEY, genreId, 1);
        setMovieListAdapter(observable);
    }

    private void searchMoviesByCast(int castId) {
        Observable observable = mMovieRepository.searchMoviesByCast(Constant.API_KEY, castId, 1);
        setMovieListAdapter(observable);
    }

    private void setMovieListAdapter(Observable observable) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        if (mAdapter instanceof MovieListAdapter) {
                            ((MovieListAdapter) mAdapter).updateData(movies);
                        }
                    }
                });
        startDisposable(disposable);
    }

    private void goMovieDetail(final Movie movie) {
        mMovieRepository.isFavoriteMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Constant.BUNDLE_MOVIE, movie);
                        bundle.putBoolean(Constant.BUNDLE_FAVORITE, aBoolean);
                        mNavigator.startActivity(MovieDetailActivity.class, bundle);
                    }
                });
    }

    public void reloadFavoriteList() {
        setMovieListAdapter(mMovieRepository.getFavoriteMovies(1));
    }
}
