package com.example.tung.moviedb_07.screen.movie_detail;

import android.view.View;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.data.model.ProductionCompany;
import com.example.tung.moviedb_07.data.source.MovieRepository;
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
    MovieRepository mMovieRepository;

    public MovieDetailViewModel(MovieRepository movieRepository, Movie movie, boolean isFavorite) {
        if (movie != null) {
            mMovie = movie;
        } else {
            mMovie = new Movie();
        }
        mFavorite = isFavorite;
        mMovieRepository = movieRepository;
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

    public void onButtonFavoriteClicked(View view) {
        if (mFavorite) {
            mMovieRepository.deleteMovie(mMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Void>() {
                        @Override
                        public void accept(Void aVoid) throws Exception {
                            mFavorite = false;
                        }
                    });
        } else {
            mMovieRepository.addMovie(mMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Void>() {
                        @Override
                        public void accept(Void aVoid) throws Exception {
                            mFavorite = true;
                        }
                    });
            mFavorite = true;
        }
    }
}
