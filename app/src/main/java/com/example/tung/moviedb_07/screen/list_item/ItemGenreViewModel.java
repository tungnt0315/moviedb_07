package com.example.tung.moviedb_07.screen.list_item;

import android.view.View;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;

/**
 * Created by tung on 6/8/17.
 */

public class ItemGenreViewModel extends BaseViewModel {

    private Genre mGenre;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener mItemClickListener;

    public ItemGenreViewModel(Genre genre,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener itemClickListener) {
        mGenre = genre;
        mItemClickListener = itemClickListener;
    }

    public String getGenre() {
        return mGenre.getName();
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mGenre);
    }
}
