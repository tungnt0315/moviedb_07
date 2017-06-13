package com.example.tung.moviedb_07.screen.search_result;

import android.content.Context;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.screen.BaseViewModel;

/**
 * Created by tung on 6/8/17.
 */

public class SearchResultViewModel extends BaseViewModel {

    private Context mContext;
    private String mSearchKeyword;

    public SearchResultViewModel(Context context, String searchKeyword) {
        mContext = context;
        mSearchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return mContext.getString(R.string.search_text) + mSearchKeyword;
    }
}
