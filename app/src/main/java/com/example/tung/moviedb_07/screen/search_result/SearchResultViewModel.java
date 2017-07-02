package com.example.tung.moviedb_07.screen.search_result;

import android.widget.AdapterView;
import com.example.tung.moviedb_07.screen.BaseViewModel;
import com.example.tung.moviedb_07.utils.Constant;

/**
 * Created by tung on 6/8/17.
 */

public class SearchResultViewModel extends BaseViewModel {

    private AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private boolean mIsSortAble;

    SearchResultViewModel(int tab, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        mIsSortAble = tab != Constant.TAB_SEARCH_BY_NAME;
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public boolean isSortAble() {
        return mIsSortAble;
    }

    public AdapterView.OnItemSelectedListener getOnItemClickListener() {
        return mOnItemSelectedListener;
    }
}
