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

    private final Context mContext;
    private final Movie mMovie;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener mItemClickListener;
    private static final int TIME_DELAY = 3000;
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
        return mContext.getString(R.string.release_date_2) + mMovie.getReleaseDate();
    }

    public String getVote() {
        return mContext.getString(R.string.label_vote)
                + mMovie.getVoteAverage()
                + " ("
                + mMovie.getVoteCount()
                + ")";
    }

    public String getImagePath() {
        return Constant.POSTER_SIZE + "/" + mMovie.getPosterPath();
    }

    @SuppressWarnings("unchecked")
    public void onItemClicked(final View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mMovie);
        view.setClickable(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setClickable(true);
            }
        }, TIME_DELAY);
    }
}
