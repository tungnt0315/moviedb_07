package com.example.tung.moviedb_07.screen.movie_detail;

import android.content.Context;
import android.databinding.Bindable;
import android.view.View;
import com.android.databinding.library.baseAdapters.BR;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.ProductionCompany;
import com.example.tung.moviedb_07.data.source.MovieRepository;
import com.example.tung.moviedb_07.data.source.local.sqlite.MovieLocalDataSource;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.utils.Constant;
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
    private Context mContext;

    public MovieDetailViewModel(Context context, Movie movie, boolean isFavorite) {
        if (movie != null) {
            mMovie = movie;
        } else {
            mMovie = new Movie();
        }
        mFavorite = isFavorite;
        mContext = context;
        mMovieRepository = new MovieRepository(new MovieLocalDataSource(context), null);
    }

    public String getTittle() {
        return mMovie.getTitle();
    }

    public String getGenres() {
        String result = "";
        for (Genre genre : mMovie.getGenres()) {
            result += genre.getName() + ", ";
        }
        return result;
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

    public String getOverview() {
        return mMovie.getOverview();
    }

    public String getBackdropPath() {
        return Constant.BACKDROP_SIZE + "/" + mMovie.getBackdropPath();
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    @Bindable
    public String getFavoriteText() {
        if (isFavorite()) {
            return mContext.getString(R.string.remove);
        } else {
            return mContext.getString(R.string.save);
        }
    }

    public void onButtonFavoriteClicked(View view) {
        if (mFavorite) {
            mMovieRepository.deleteMovie(mMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            mFavorite = false;
                            notifyPropertyChanged(BR.favoriteText);
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
                            notifyPropertyChanged(BR.favoriteText);
                        }
                    });
            mFavorite = true;
        }
    }
}
