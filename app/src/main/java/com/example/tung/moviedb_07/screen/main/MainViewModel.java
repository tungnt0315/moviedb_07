package com.example.tung.moviedb_07.screen.main;

import com.example.tung.moviedb_07.screen.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    private MainViewPagerAdapter mViewPagerAdapter;

    public MainViewModel(MainViewPagerAdapter viewPagerAdapter) {
        mViewPagerAdapter = viewPagerAdapter;
    }

    public MainViewPagerAdapter getAdapter() {
        return mViewPagerAdapter;
    }
}
