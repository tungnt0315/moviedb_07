package com.example.tung.moviedb_07.screen.list_item;

import android.content.Context;
import android.os.Bundle;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.MovieList;
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
        implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener, OnLoadMoreListener {

    private BaseRecyclerViewAdapter mAdapter;
    private Navigator mNavigator;
    private MovieRepository mMovieRepository;
    private int mTab;
    private int mPage = 2;
    private int mPageMax;
    private Object mObjSearch;

    public ListItemViewModel(Context context, Bundle bundle, Navigator navigator) {
        mMovieRepository = new MovieRepository(new MovieLocalDataSource(context),
                new MovieRemoteDataSource(MovieServiceClient.getInstance()));
        mTab = bundle.getInt(Constant.BUNDLE_TAB);
        switch (mTab) {
            case Constant.TAB_SEARCH_BY_NAME:
                mObjSearch = bundle.getString(Constant.BUNDLE_SEARCH_KEYWORD);
                break;
            case Constant.TAB_SEARCH_BY_GENRE:
                mObjSearch = bundle.getInt(Constant.BUNDLE_GENRE_ID);
                break;
            case Constant.TAB_SEARCH_BY_CAST:
            case Constant.TAB_SEARCH_BY_CREW:
                mObjSearch = bundle.getInt(Constant.BUNDLE_PERSON_ID);
        }
        loadDataFirstPage(context);
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
            mMovieRepository.getMovieDetails(((Movie) item).getId())
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

    @Override
    public void onLoadMore() {
        if (mPage <= mPageMax) {
            Observable<List<Movie>> observable;
            switch (mTab) {
                case Constant.TAB_POPULAR:
                case Constant.TAB_NOW_PLAYING:
                case Constant.TAB_UPCOMING:
                case Constant.TAB_TOP_RATED:
                    observable = mMovieRepository.getMovies(mTab, null, mPage);
                    break;
                case Constant.TAB_SEARCH_BY_NAME:
                case Constant.TAB_SEARCH_BY_GENRE:
                case Constant.TAB_SEARCH_BY_CAST:
                case Constant.TAB_SEARCH_BY_CREW:
                    observable = mMovieRepository.getMovies(mTab, mObjSearch, mPage);
                    break;
                default: // TAB_FAVORITE
                    observable = mMovieRepository.getFavoriteMovies(mPage);
            }
            loadMoreMovieData(observable);
            if (mPage == mPageMax) {
                ((MovieListAdapter) mAdapter).setLoadCompleted(true);
            }
        }
    }

    private void loadDataFirstPage(Context context) {
        if (mTab == Constant.TAB_GENRE) { // list of genres
            mAdapter = new GenreListAdapter(context);
            Disposable disposable = mMovieRepository.getGenres()
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
        } else {
            mAdapter = new MovieListAdapter(context);
            ((MovieListAdapter) mAdapter).setOnLoadmoreListener(this);
            Observable<MovieList> observable;
            switch (mTab) {
                case Constant.TAB_POPULAR:
                case Constant.TAB_NOW_PLAYING:
                case Constant.TAB_UPCOMING:
                case Constant.TAB_TOP_RATED:
                    observable = mMovieRepository.getMovieList(mTab, null);
                    break;
                case Constant.TAB_SEARCH_BY_NAME:
                case Constant.TAB_SEARCH_BY_GENRE:
                case Constant.TAB_SEARCH_BY_CAST:
                case Constant.TAB_SEARCH_BY_CREW:
                    observable = mMovieRepository.getMovieList(mTab, mObjSearch);
                    break;
                default:    // TAB_FAVORITE
                    observable = mMovieRepository.getFavoriteList();
            }
            loadMovieAdapterFirstPage(observable);
        }
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    public void reloadFavoriteList() {
        loadMovieAdapterFirstPage(mMovieRepository.getFavoriteList());
    }

    private void loadMovieAdapterFirstPage(Observable<MovieList> observable) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieList>() {
                    @Override
                    public void accept(MovieList movieList) throws Exception {
                        if (mAdapter instanceof MovieListAdapter) {
                            mPageMax = movieList.getTotalPages();
                            ((MovieListAdapter) mAdapter).updateData(movieList.getMovies());
                        }
                    }
                });
        startDisposable(disposable);
    }

    private void loadMoreMovieData(Observable<List<Movie>> observable) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        if (mAdapter instanceof MovieListAdapter) {
                            ((MovieListAdapter) mAdapter).addData(movies);
                            mPage++;
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
}
