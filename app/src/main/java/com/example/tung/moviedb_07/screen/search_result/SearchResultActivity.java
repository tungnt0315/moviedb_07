package com.example.tung.moviedb_07.screen.search_result;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.databinding.ActivitySearchResultBinding;
import com.example.tung.moviedb_07.screen.list_item.ListItemFragment;
import com.example.tung.moviedb_07.utils.Constant;

/**
 * Created by tung on 6/12/17.
 */

public class SearchResultActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private Bundle mBundle;
    private String mSortBy;
    private boolean mIsChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getIntent().getExtras();
        getSupportActionBar().setTitle(
                this.getString(R.string.search_result_title) + mBundle.getString(
                        Constant.BUNDLE_SEARCH_KEYWORD));
        mSortBy = getString(R.string.sort_by_default);
        setFragment(mBundle, mSortBy);
        SearchResultViewModel viewModel =
                new SearchResultViewModel(mBundle.getInt(Constant.BUNDLE_TAB), this);
        ActivitySearchResultBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (mIsChange) {
            mSortBy = getResources().getStringArray(R.array.sort_by_values)[position];
            setFragment(mBundle, mSortBy);
        } else {
            mIsChange = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setFragment(Bundle bundle, String sortBy) {
        bundle.putString(Constant.BUNDLE_SORT_BY, sortBy);
        ListItemFragment fragment = new ListItemFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_search_result, fragment);
        transaction.commit();
    }
}
