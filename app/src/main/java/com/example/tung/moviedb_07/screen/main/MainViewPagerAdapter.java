package com.example.tung.moviedb_07.screen.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.tung.moviedb_07.screen.list_item.ListItemFragment;
import com.example.tung.moviedb_07.utils.Constant;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ListItemFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putInt(Constant.BUNDLE_TAB, 0);
                break;
            case 1:
                bundle.putInt(Constant.BUNDLE_TAB, 1);
                break;
            case 2:
                bundle.putInt(Constant.BUNDLE_TAB, 2);
                break;
            case 3:
                bundle.putInt(Constant.BUNDLE_TAB, 3);
                break;
            case 4:
                bundle.putInt(Constant.BUNDLE_TAB, 4);
                break;
            case 5:
                bundle.putInt(Constant.BUNDLE_TAB, 5);
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Popular";
                break;
            case 1:
                title = "Now Playing";
                break;
            case 2:
                title = "Upcomming";
                break;
            case 3:
                title = "Top Rate";
                break;
            case 4:
                title = "Genres";
                break;
            case 5:
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
