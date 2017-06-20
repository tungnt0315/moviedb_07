package com.example.tung.moviedb_07.screen.movie_detail;

import android.content.Context;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;
import com.example.tung.moviedb_07.BR;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Cast;
import com.example.tung.moviedb_07.data.model.Crew;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.ProductionCompany;
import com.example.tung.moviedb_07.data.source.MovieRepository;
import com.example.tung.moviedb_07.data.source.local.sqlite.MovieLocalDataSource;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.screen.search_result.SearchResultActivity;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;
import com.greenfrvr.hashtagview.HashtagView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by tung on 6/12/17.
 */

public class MovieDetailViewModel extends BaseViewModel {

    private Movie mMovie;
    private boolean mFavorite;
    private MovieRepository mMovieRepository;
    private Navigator mNavigator;
    private Context mContext;

    public MovieDetailViewModel(Context context, Movie movie, boolean isFavorite,
            Navigator navigator) {
        if (movie != null) {
            mMovie = movie;
        } else {
            mMovie = new Movie();
        }
        mContext = context;
        mFavorite = isFavorite;
        mNavigator = navigator;
        mMovieRepository = new MovieRepository(new MovieLocalDataSource(context), null);
    }

    public String getTittle() {
        return mMovie.getTitle();
    }

    public String getReleaseDate() {
        return mMovie.getReleaseDate();
    }

    public String getProduction() {
        List<ProductionCompany> productions = mMovie.getProductionCompanies();
        if (productions != null && !productions.isEmpty() && productions.get(0) != null) {
            return mMovie.getProductionCompanies().get(0).getName();
        }
        return "";
    }

    public String getVoteCount() {
        return String.valueOf(mMovie.getVoteCount());
    }

    public String getVoteAverage() {
        return String.valueOf(mMovie.getVoteAverage());
    }

    public String getOverview() {
        return mMovie.getOverview();
    }

    public List<Genre> getGenres() {
        return mMovie.getGenres();
    }

    public Crew getDirector() {
        Crew director = new Crew();
        for (Crew crew : mMovie.getCredits().getCrews()) {
            String directorJob = mContext.getString(R.string.label_director);
            if (crew.getJob().equals(directorJob)) {
                director = crew;
                break;
            }
        }
        return director;
    }

    public List<Cast> getCasts() {
        if (mMovie.getCredits() == null || mMovie.getCredits().getCasts() == null) {
            return null;
        }
        List<Cast> casts = mMovie.getCredits().getCasts();
        if (casts.size() > 10) {
            casts = casts.subList(0, 10);
        }
        return casts;
    }

    @Bindable
    public boolean isFavorite() {
        return mFavorite;
    }

    public Navigator getNavigator() {
        return mNavigator;
    }

    public String getVideoKey() {
        if (mMovie.getVideoList() == null
                || mMovie.getVideoList().getVideos() == null
                || mMovie.getVideoList().getVideos().isEmpty()) {
            return null;
        }
        return mMovie.getVideoList().getVideos().get(0).getKey();
    }

    public HashtagView.TagsClickListener getTagsClickListener() {
        return new HashtagView.TagsClickListener() {
            @Override
            public void onItemClicked(Object item) {
                Bundle bundle = new Bundle();
                if (item instanceof Genre) {
                    bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_SEARCH_BY_GENRE);
                    bundle.putInt(Constant.BUNDLE_GENRE_ID, ((Genre) item).getId());
                    bundle.putString(Constant.BUNDLE_SEARCH_KEYWORD, ((Genre) item).getName());
                } else {
                    if (item instanceof Cast) {
                        bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_SEARCH_BY_CAST);
                        bundle.putInt(Constant.BUNDLE_PERSON_ID, ((Cast) item).getId());
                        bundle.putString(Constant.BUNDLE_SEARCH_KEYWORD, ((Cast) item).getName());
                    } else {
                        bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_SEARCH_BY_CREW);
                        bundle.putInt(Constant.BUNDLE_PERSON_ID, ((Crew) item).getId());
                        bundle.putString(Constant.BUNDLE_SEARCH_KEYWORD, ((Crew) item).getName());
                    }
                }
                mNavigator.startActivity(SearchResultActivity.class, bundle);
            }
        };
    }

    public void onImageFavoriteClicked(View view) {
        if (mFavorite) {
            mMovieRepository.deleteMovie(mMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            mFavorite = false;
                            notifyPropertyChanged(BR.favorite);
                        }
                    });
        } else {
            mMovieRepository.addMovie(mMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            mFavorite = true;
                            notifyPropertyChanged(BR.favorite);
                        }
                    });
            mFavorite = true;
        }
    }
}
