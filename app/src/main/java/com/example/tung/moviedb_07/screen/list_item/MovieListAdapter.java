package com.example.tung.moviedb_07.screen.list_item;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.databinding.ItemMovieBinding;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends BaseRecyclerViewAdapter<MovieListAdapter.ItemViewholder> {

    private List<Movie> mMovies;

    protected MovieListAdapter(@NonNull Context context, List<Movie> movies) {
        super(context);
        movies = new ArrayList<>();
        if (movies != null) movies.addAll(movies);
    }

    @Override
    public ItemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie, parent, false);
        return new ItemViewholder(binding, mClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewholder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public class ItemViewholder extends RecyclerView.ViewHolder {

        private ItemMovieBinding mBinding;
        OnRecyclerViewItemClickListener mItemClickListener;

        public ItemViewholder(ItemMovieBinding binding,
                OnRecyclerViewItemClickListener itemClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = itemClickListener;
        }

        public void bind(Movie movie) {
            mBinding.setViewModel(new ItemMovieViewModel(movie, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
