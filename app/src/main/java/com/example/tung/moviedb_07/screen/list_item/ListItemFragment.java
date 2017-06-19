package com.example.tung.moviedb_07.screen.list_item;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.databinding.FragmentListItemBinding;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;

/**
 * Created by tung on 6/8/17.
 */

public class ListItemFragment extends Fragment {

    private ListItemViewModel mViewModel;
    private int mTab;

    public ListItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentListItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_item, container, false);
        Navigator navigator = new Navigator(this);
        mViewModel = new ListItemViewModel(getActivity().getApplicationContext(), getArguments(),
                navigator);
        View view = binding.getRoot();
        binding.setViewModel(mViewModel);
        mTab = getArguments().getInt(Constant.BUNDLE_TAB);
        return view;
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    // reload favorite list
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mTab == Constant.TAB_FAVORITE && isAdded() && isVisible()) {
            mViewModel.reloadFavoriteList();
        }
    }
}
