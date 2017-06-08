package com.example.tung.moviedb_07.screen.list_item;

import android.view.View;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;

/**
 * Created by tung on 6/8/17.
 */

public class ItemMovieViewModel extends BaseViewModel {

    private Movie mMovie;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener mItemClickListener;

    public ItemMovieViewModel(Movie movie,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener itemClickListener) {
        mMovie = movie;
        mItemClickListener = itemClickListener;
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public String getReleaseDate() {
        return mMovie.getReleaseDate();
    }

    public String getVoteCount() {
        return mMovie.getVoteCount().toString();
    }

    public String getVoteAverage() {
        return mMovie.getVoteAverage().toString();
    }

    public String getImagePath() {
        return mMovie.getBackdropPath();
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mMovie);
    }
}
