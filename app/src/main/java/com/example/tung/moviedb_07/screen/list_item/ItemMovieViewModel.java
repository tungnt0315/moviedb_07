package com.example.tung.moviedb_07.screen.list_item;

import android.content.Context;
import android.view.View;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.utils.Constant;

/**
 * Created by tung on 6/8/17.
 */

public class ItemMovieViewModel extends BaseViewModel {

    private Movie mMovie;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener mItemClickListener;
    Context mContext;

    public ItemMovieViewModel(Context context, Movie movie,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener itemClickListener) {
        mContext = context;
        mMovie = movie;
        mItemClickListener = itemClickListener;
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public String getReleaseDate() {
        return mContext.getString(R.string.release_date) + mMovie.getReleaseDate();
    }

    public String getVote() {
        return mContext.getString(R.string.vote) + mMovie.getVoteAverage() + " (" + mMovie.getVoteCount() + ")";
    }

    public String getImagePath() {
        return Constant.POSTER_SIZE + "/" + mMovie.getPosterPath();
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mMovie);
    }
}
