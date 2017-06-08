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
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import com.example.tung.moviedb_07.utils.Constant;
import com.example.tung.moviedb_07.utils.navigator.Navigator;

public class ListItemFragment extends Fragment {

    private ListItemViewModel mViewModel;

    public ListItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentListItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_item, container, false);
        BaseRecyclerViewAdapter adapter;
        int tab = getArguments().getInt(Constant.BUNDLE_TAB);
        if (tab == 4) {
            adapter = new GenreListAdapter(this.getContext(), null);
        } else {
            adapter = new MovieListAdapter(getContext(), null);
        }
        Navigator navigator = new Navigator(this);
        mViewModel = new ListItemViewModel(adapter, navigator);
        View v = binding.getRoot();
        binding.setViewModel(mViewModel);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mViewModel.onStop();
    }
}
