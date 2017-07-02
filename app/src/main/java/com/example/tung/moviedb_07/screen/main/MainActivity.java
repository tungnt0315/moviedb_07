package com.example.tung.moviedb_07.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.databinding.ActivityMainBinding;
import com.example.tung.moviedb_07.screen.search_result.SearchResultActivity;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;

/**
 * Created by tung on 6/8/17.
 */

public class MainActivity extends AppCompatActivity {

    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(this.getString(R.string.main_title));
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        MainViewModel viewModel = new MainViewModel(adapter);
        mNavigator = new Navigator(this);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_SEARCH_BY_NAME);
                bundle.putString(Constant.BUNDLE_SEARCH_KEYWORD, query);
                mNavigator.startActivity(SearchResultActivity.class, bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
