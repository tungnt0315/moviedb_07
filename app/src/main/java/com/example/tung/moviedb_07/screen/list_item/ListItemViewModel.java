package com.example.tung.moviedb_07.screen.list_item;

import android.content.Context;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.tung.moviedb_07.BR;
import com.example.tung.moviedb_07.R;
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
import com.example.tung.moviedb_07.widget.dialog.DialogManager;
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

    private Context mContext;
    private GenreRecyclerAdapter mGenreAdapter;
    private MovieRecyclerAdapter mMovieAdapterLinear, mMovieAdapterGrid;
    private Navigator mNavigator;
    private MovieRepository mMovieRepository;
    private DialogManager mDialogManager;
    private Object mObjSearch;
    private String mSortBy;
    private int mTab;
    private int mPage = 2;
    private int mPageMax;
    private boolean mIsImageUpVisible;
    private static boolean sIsGridLayout;

    ListItemViewModel(Context context, Bundle bundle, Navigator navigator,
            DialogManager dialogManager) {
        mContext = context;
        mMovieRepository = new MovieRepository(new MovieLocalDataSource(context),
                new MovieRemoteDataSource(MovieServiceClient.getInstance()));
        mTab = bundle.getInt(Constant.BUNDLE_TAB);
        switch (mTab) {
            case Constant.TAB_SEARCH_BY_NAME:
                mObjSearch = bundle.getString(Constant.BUNDLE_SEARCH_KEYWORD);
                break;
            case Constant.TAB_SEARCH_BY_GENRE:
                mSortBy = bundle.getString(Constant.BUNDLE_SORT_BY);
                mObjSearch = bundle.getInt(Constant.BUNDLE_GENRE_ID);
                break;
            case Constant.TAB_SEARCH_BY_CAST:
            case Constant.TAB_SEARCH_BY_CREW:
                mSortBy = bundle.getString(Constant.BUNDLE_SORT_BY);
                mObjSearch = bundle.getInt(Constant.BUNDLE_PERSON_ID);
        }
        mDialogManager = dialogManager;
        loadDataFirstPage();
        mNavigator = navigator;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (mTab == Constant.TAB_GENRE) {
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
            mDialogManager.showProgressDialog();
            mMovieRepository.getMovieDetails(((Movie) item).getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Movie>() {
                        @Override
                        public void accept(Movie movie) throws Exception {
                            goMovieDetail(movie);
                            mDialogManager.dismissProgressDialog();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mDialogManager.dismissProgressDialog();
                        }
                    });
        }
    }

    @Override
    public void onLoadMore() {
        if (mPage <= mPageMax) {
            Observable<List<Movie>> observable;
            if (mTab == Constant.TAB_FAVORITE) {
                observable = mMovieRepository.getFavoriteMovies(mPage);
            } else {
                observable = mMovieRepository.getMovies(mTab, mObjSearch, mSortBy, mPage);
            }
            loadMoreMovieAdapter(observable);
            if (mPage == mPageMax) {
                mMovieAdapterLinear.setLoadCompleted(true);
                mMovieAdapterGrid.setLoadCompleted(true);
            }
        }
    }

    @Bindable
    public boolean getIsGridLayout() {
        return sIsGridLayout;
    }

    @Bindable
    public boolean getLinearLayoutVisibility() {
        return mTab == Constant.TAB_GENRE || !sIsGridLayout;
    }

    @Bindable
    public boolean getImageUpVisibility() {
        return mIsImageUpVisible;
    }

    @Bindable
    public int getScrollToFirst() {
        return 0; // return first item position (= 0)
    }

    public BaseRecyclerViewAdapter getAdapterLinear() {
        if (mTab == Constant.TAB_GENRE) {
            return mGenreAdapter;
        }
        return mMovieAdapterLinear;
    }

    public BaseRecyclerViewAdapter getAdapterGrid() {
        return mMovieAdapterGrid;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position =
                        ((LinearLayoutManager) recyclerView.getLayoutManager())
                                .findFirstVisibleItemPosition();
                if (position == 0) {
                    mIsImageUpVisible = false;
                    notifyPropertyChanged(BR.imageUpVisibility);
                } else if (!mIsImageUpVisible) {
                    mIsImageUpVisible = true;
                    notifyPropertyChanged(BR.imageUpVisibility);
                }
            }
        };
    }

    public void onImageUpClicked(View view) {
        notifyPropertyChanged(BR.scrollToFirst);
    }

    /**
     * @return return true if layout changed, otherwise return false
     */
    void reloadMovieAdapter() {
        if (mTab == Constant.TAB_FAVORITE) { // reload favorite list when have change
            loadMovieAdapterFirstPage(mMovieRepository.getFavoriteList());
        } else if (mTab != Constant.TAB_GENRE) {
            // change layout of tab when layout of previous tab changed
            notifyPropertyChanged(BR.linearLayoutVisibility);
        }
    }

    boolean changeLayoutManager() {
        if (mTab == Constant.TAB_GENRE) {
            return false;
        }
        sIsGridLayout = !sIsGridLayout;
        notifyPropertyChanged(BR.linearLayoutVisibility);
        return sIsGridLayout;
    }

    private void loadDataFirstPage() {
        if (mTab == Constant.TAB_GENRE) { // list of genres
            mGenreAdapter = new GenreRecyclerAdapter(mContext);
            mGenreAdapter.setClickListener(this);
            Disposable disposable = mMovieRepository.getGenres()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Genre>>() {
                        @Override
                        public void accept(List<Genre> genres) throws Exception {
                            mGenreAdapter.updateData(genres);
                        }
                    });
            startDisposable(disposable);
        } else {
            mMovieAdapterLinear = new MovieRecyclerAdapter(mContext, false);
            mMovieAdapterGrid = new MovieRecyclerAdapter(mContext, true);
            mMovieAdapterLinear.setClickListener(this);
            mMovieAdapterGrid.setClickListener(this);
            mMovieAdapterLinear.setOnLoadmoreListener(this);
            mMovieAdapterGrid.setOnLoadmoreListener(this);
            Observable<MovieList> observable;
            if (mTab == Constant.TAB_FAVORITE) {
                observable = mMovieRepository.getFavoriteList();
            } else {
                observable = mMovieRepository.getMovieList(mTab, mObjSearch, mSortBy);
            }
            loadMovieAdapterFirstPage(observable);
        }
    }

    private void loadMovieAdapterFirstPage(Observable<MovieList> observable) {
        mDialogManager.showProgressDialog();
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieList>() {
                    @Override
                    public void accept(MovieList movieList) throws Exception {
                        mPageMax = movieList.getTotalPages();
                        mMovieAdapterLinear.updateData(movieList.getMovies());
                        mMovieAdapterGrid.updateData(movieList.getMovies());
                        if (mTab >= Constant.TAB_SEARCH_BY_NAME && (movieList.getMovies()
                                .isEmpty())) {
                            mNavigator.showMessage(mContext.getString(R.string.message_no_movie));
                        }
                        mDialogManager.dismissProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mDialogManager.dismissProgressDialog();
                    }
                });
        startDisposable(disposable);
    }

    private void loadMoreMovieAdapter(Observable<List<Movie>> observable) {
        mDialogManager.showProgressDialog();
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        mMovieAdapterLinear.addData(movies);
                        mMovieAdapterGrid.addData(movies);
                        mPage++;
                        mDialogManager.dismissProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mDialogManager.dismissProgressDialog();
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
