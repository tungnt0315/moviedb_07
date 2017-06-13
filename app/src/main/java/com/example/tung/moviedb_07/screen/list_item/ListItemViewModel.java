package com.example.tung.moviedb_07.screen.list_item;

import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;

/**
 * Created by tung on 6/8/17.
 */

public class ListItemViewModel extends BaseViewModel
        implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private BaseRecyclerViewAdapter mAdapter;
    private Navigator mNavigator;

    public ListItemViewModel(int tab, Navigator navigator, String searchKeyword) {
        if (tab == Constant.TAB_GENRE) { // list of genres
            mAdapter = new GenreListAdapter(navigator.getActivity());
            getGenres();
        } else if (tab == Constant.TAB_SEARCH_RESULT) {  // list result of search movies
            mAdapter = new MovieListAdapter(navigator.getActivity());
            searchMovies(searchKeyword);
        } else {    // list of movies
            mAdapter = new MovieListAdapter(navigator.getActivity());
            getListMovies(tab);
        }
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

    private void getGenres() {
        // TODO code get list of genre and set data for adapter.
    }

    private void getListMovies(int tab) {
        // TODO code get list of movies and set data for adapter.
    }

    private void searchMovies(String searchKeyword) {
        // TODO code search movies and set data for adapter.
    }
}
