package com.example.tung.moviedb_07.screen.list_item;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.source.local.sqlite.MovieDbHelper;
import com.example.tung.moviedb_07.databinding.FragmentListItemBinding;
import com.example.tung.moviedb_07.utils.navigator.Navigator;
import com.example.tung.moviedb_07.widget.dialog.DialogManager;
import com.example.tung.moviedb_07.widget.dialog.DialogManagerImpl;

/**
 * Created by tung on 6/8/17.
 */

public class ListItemFragment extends Fragment {

    private ListItemViewModel mViewModel;
    private static MenuItem sItemChangeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentListItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_item, container, false);
        Navigator navigator = new Navigator(this);
        DialogManager dialogManager = new DialogManagerImpl(this.getContext());
        mViewModel = new ListItemViewModel(getActivity().getApplicationContext(), getArguments(),
                navigator, dialogManager);
        View view = binding.getRoot();
        binding.setViewModel(mViewModel);
        // To set event for button on actionbar.
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        MovieDbHelper.getInstance(getActivity().getApplicationContext()).close();
        super.onStop();
    }

    // reload movie list when return page
    @Override
    public void onResume() {
        super.onResume();
        if (isAdded() && isVisible()) {
            mViewModel.reloadMovieAdapter();
        }
    }

    // reload movie list when go previous or next fragment
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isAdded() && isVisibleToUser) {
            mViewModel.reloadMovieAdapter();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        sItemChangeLayout = menu.findItem(R.id.change_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_layout) {
            boolean isGridLayout = mViewModel.changeLayoutManager();
            changeIconLayout(isGridLayout);
        }
        return false;
    }

    // change icon layout when go previous or next fragment
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        changeIconLayout(mViewModel.getIsGridLayout());
    }

    private void changeIconLayout(boolean isGridLayout) {
        if (isGridLayout) {
            sItemChangeLayout.setIcon(R.drawable.icon_linear);
        } else {
            sItemChangeLayout.setIcon(R.drawable.icon_grid);
        }
    }
}
