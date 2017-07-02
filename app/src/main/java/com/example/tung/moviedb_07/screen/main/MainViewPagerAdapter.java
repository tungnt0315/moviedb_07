package com.example.tung.moviedb_07.screen.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.tung.moviedb_07.screen.list_item.ListItemFragment;
import com.example.tung.moviedb_07.utils.Constant;

/**
 * Created by tung on 6/8/17.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ListItemFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_POPULAR);
                break;
            case 1:
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_NOW_PLAYING);
                break;
            case 2:
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_UPCOMING);
                break;
            case 3:
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_TOP_RATED);
                break;
            case 4:
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_GENRE);
                break;
            case 5:
                bundle.putInt(Constant.BUNDLE_TAB, Constant.TAB_FAVORITE);
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case Constant.TAB_POPULAR:
                title = "Popular";
                break;
            case Constant.TAB_NOW_PLAYING:
                title = "Now Playing";
                break;
            case Constant.TAB_UPCOMING:
                title = "Upcomming";
                break;
            case Constant.TAB_TOP_RATED:
                title = "Top Rate";
                break;
            case Constant.TAB_GENRE:
                title = "Genres";
                break;
            case Constant.TAB_FAVORITE:
                title = "Favorite";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
