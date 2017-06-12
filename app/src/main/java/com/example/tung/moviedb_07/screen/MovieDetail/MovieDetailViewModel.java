package com.example.tung.moviedb_07.screen.MovieDetail;

import android.view.View;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.screen.BaseViewModel;

/**
 * Created by tung on 6/12/17.
 */

public class MovieDetailViewModel extends BaseViewModel {

    private Movie mMovie;
    private boolean mFavorite;

    public MovieDetailViewModel(Movie movie, boolean isFavorite) {
        if (movie != null) {
            mMovie = movie;
        } else {
            mMovie = new Movie();
        }
        mFavorite = isFavorite;
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
        return mMovie.getProductionCompanies().get(0).getName();
    }

    public String getOverview() {
        return mMovie.getOverview();
    }

    public String getBackdropPath() {
        return mMovie.getBackdropPath();
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    public void onButtonFavoriteClicked(View view) {
        if (mFavorite) {
            // TODO delete movie from database
            mFavorite = false;
        } else {
            // TODO add movie to database
            mFavorite = true;
        }
    }
}
