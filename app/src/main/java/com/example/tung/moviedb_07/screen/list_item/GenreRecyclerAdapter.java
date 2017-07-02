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

/**
 * Created by tung on 6/8/17.
 */

class GenreRecyclerAdapter extends BaseRecyclerViewAdapter<GenreRecyclerAdapter.ItemViewHolder> {

    private List<Genre> mGenres;

    GenreRecyclerAdapter(@NonNull Context context) {
        super(context);
        mGenres = new ArrayList<>();
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

    void updateData(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    /**
     * ItemViewHolder.
     */
    final class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemGenreBinding mBinding;
        private OnRecyclerViewItemClickListener mItemClickListener;

        private ItemViewHolder(ItemGenreBinding binding,
                OnRecyclerViewItemClickListener itemClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = itemClickListener;
        }

        private void bind(Genre genre) {
            mBinding.setViewModel(new ItemGenreViewModel(genre, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
