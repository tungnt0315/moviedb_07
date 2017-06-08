package com.example.tung.moviedb_07.screen.list_item;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Genre;
import com.example.tung.moviedb_07.databinding.ItemGenreBinding;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class GenreListAdapter extends BaseRecyclerViewAdapter<GenreListAdapter.ItemViewHolder> {

    private List<Genre> mGenres;

    protected GenreListAdapter(@NonNull Context context, List<Genre> genres) {
        super(context);
        mGenres = new ArrayList<>();
        if (genres != null) mGenres.addAll(genres);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_genre, parent, false);
        return new ItemViewHolder(binding, mClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemGenreBinding mBinding;
        OnRecyclerViewItemClickListener mItemClickListener;

        public ItemViewHolder(ItemGenreBinding binding,
                OnRecyclerViewItemClickListener itemClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = itemClickListener;
        }

        public void bind(Genre genre) {
            mBinding.setViewModel(new ItemGenreViewModel(genre, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
