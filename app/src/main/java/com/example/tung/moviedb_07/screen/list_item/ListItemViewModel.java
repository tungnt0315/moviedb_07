package com.example.tung.moviedb_07.screen.list_item;

import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.utils.navigator.Navigator;

/**
 * Created by tung on 6/8/17.
 */

public class ListItemViewModel extends BaseViewModel
        implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    BaseRecyclerViewAdapter mAdapter;
    private Navigator mNavigator;

    public ListItemViewModel(BaseRecyclerViewAdapter adapter, Navigator navigator) {
        mAdapter = adapter;
        mAdapter.setClickListener(this);
        mNavigator = navigator;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (mAdapter instanceof GenreListAdapter) {
            // TODO code event click genre here.
        } else {
            // TODO code event click movie here.
        }
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }
}
