package com.example.tung.moviedb_07.screen.search_result;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.databinding.ActivitySearchResultBinding;
import com.example.tung.moviedb_07.screen.list_item.ListItemFragment;
import com.example.tung.moviedb_07.utils.Constant;

/**
 * Created by tung on 6/12/17.
 */

public class SearchResultActivity extends AppCompatActivity {

    private SearchResultViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_SEARCH_RESULT);
        ListItemFragment fragment = new ListItemFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_search_result, fragment);
        transaction.commit();

        ActivitySearchResultBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        mViewModel =
                new SearchResultViewModel(this, bundle.getString(Constant.BUNDLE_SEARCH_KEYWORD));
        binding.setViewModel(mViewModel);
    }
}
