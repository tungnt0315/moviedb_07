package com.example.tung.moviedb_07.screen.list_item;

import android.os.Bundle;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.source.MovieRepository;
import com.example.tung.moviedb_07.data.source.remote.MovieRemoteDataSource;
import com.example.tung.moviedb_07.data.source.remote.api.service.MovieServiceClient;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.screen.movie_detail.MovieDetailActivity;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

import static com.example.tung.moviedb_07.utils.Constant.API_KEY;
import static com.example.tung.moviedb_07.utils.Constant.BUNDLE_MOVIE;
import static com.example.tung.moviedb_07.utils.Constant.TAB_NOW_PLAYING;
import static com.example.tung.moviedb_07.utils.Constant.TAB_POPULAR;
import static com.example.tung.moviedb_07.utils.Constant.TAB_TOP_RATED;
import static com.example.tung.moviedb_07.utils.Constant.TAB_UPCOMING;

/**
 * Created by tung on 6/8/17.
 */

public class ListItemViewModel extends BaseViewModel
        implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private BaseRecyclerViewAdapter mAdapter;
    private Navigator mNavigator;
    private MovieRepository mMovieRepository;

    public ListItemViewModel(int tab, Navigator navigator, String searchKeyword) {
        mMovieRepository = new MovieRepository(null,
                new MovieRemoteDataSource(MovieServiceClient.getInstance()));
        if (tab == Constant.TAB_GENRE) { // list of genres
            mAdapter = new GenreListAdapter(navigator.getActivity());
            getGenres();
        } else if (tab == Constant.TAB_SEARCH_RESULT) {  // list result of search movies
            mAdapter = new MovieListAdapter(navigator.getActivity());
            searchMovies(searchKeyword);
        } else {    // list of movies
            mAdapter = new MovieListAdapter(navigator.getActivity());
            getListMovies(tab);
        }
        mAdapter.setClickListener(this);
        mNavigator = navigator;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (mAdapter instanceof GenreListAdapter) {
            // TODO code event click genre here.
        } else {
            if (!(item instanceof Movie)) {
                return;
            }
            mMovieRepository.getMovieDetails(((Movie) item).getId(), API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Movie>() {
                        @Override
                        public void accept(Movie movie) throws Exception {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(BUNDLE_MOVIE, movie);
                            mNavigator.startActivity(MovieDetailActivity.class, bundle);
                        }
                    });
        }
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    private void getGenres() {
        Disposable disposable = mMovieRepository.getGenres(API_KEY)
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
            case TAB_POPULAR:
                // 1 is page number, it will be replaced when i use onLoadMore for recyclerview
                observable = mMovieRepository.getPopularMovies(API_KEY, 1);
                break;
            case TAB_NOW_PLAYING:
                observable = mMovieRepository.getNowPlayingMovies(API_KEY, 1);
                break;
            case TAB_UPCOMING:
                observable = mMovieRepository.getUpcomingMovies(API_KEY, 1);
                break;
            case TAB_TOP_RATED:
                observable = mMovieRepository.getTopRatedMovies(API_KEY, 1);
                break;
            default:
                // TODO code get movies from database.
                observable = mMovieRepository.getTopRatedMovies(API_KEY, 1);
                break;
        }
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

    private void searchMovies(String searchKeyword) {
        Disposable disposable = mMovieRepository.searchMoviesByName(API_KEY, searchKeyword, 1)
                .subscribeOn(Schedulers.io())
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
}
