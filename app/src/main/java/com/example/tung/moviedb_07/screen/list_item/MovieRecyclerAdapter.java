package com.example.tung.moviedb_07.screen.list_item;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.tung.moviedb_07.R;
import com.example.tung.moviedb_07.data.model.Movie;
import com.example.tung.moviedb_07.databinding.ItemMovieGridBinding;
import com.example.tung.moviedb_07.databinding.ItemMovieLinearBinding;
import com.example.tung.moviedb_07.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

class MovieRecyclerAdapter
        extends BaseRecyclerViewAdapter<MovieRecyclerAdapter.ItemViewholder> {

    private Context mContext;
    private OnLoadMoreListener mOnLoadmoreListener;
    private List<Movie> mMovies;
    private boolean mIsLoadCompleted;
    private boolean mIsGridLayout;

    MovieRecyclerAdapter(@NonNull Context context, boolean isGridLayout) {
        super(context);
        mContext = context;
        mMovies = new ArrayList<>();
        mIsGridLayout = isGridLayout;
    }

    @Override
    public ItemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mIsGridLayout) {
            ItemMovieGridBinding gridBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_movie_grid, parent, false);
            return new ItemViewholder(gridBinding, mClickListener);
        } else {
            ItemMovieLinearBinding linearBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_movie_linear, parent, false);
            return new ItemViewholder(linearBinding, mClickListener);
        }
    }

    @Override
    public void onBindViewHolder(ItemViewholder holder, int position) {
        if (position >= getItemCount() - 1 && !mIsLoadCompleted && mOnLoadmoreListener != null) {
            mOnLoadmoreListener.onLoadMore();
        }
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    void addData(List<Movie> movies) {
        if (movies != null) {
            mMovies.addAll(movies);
            notifyDataSetChanged();
        }
    }

    void updateData(List<Movie> movies) {
        if (movies != null) {
            mMovies.clear();
            mMovies.addAll(movies);
            notifyDataSetChanged();
        }
    }

    void setLoadCompleted(boolean loadCompleted) {
        mIsLoadCompleted = loadCompleted;
    }

    void setOnLoadmoreListener(OnLoadMoreListener onLoadmoreListener) {
        mOnLoadmoreListener = onLoadmoreListener;
    }

    /**
     * ItemViewHolder.
     */
    final class ItemViewholder extends RecyclerView.ViewHolder {

        private ItemMovieLinearBinding mLinearBinding;
        private ItemMovieGridBinding mGridBinding;
        private OnRecyclerViewItemClickListener mItemClickListener;

        private ItemViewholder(ItemMovieLinearBinding binding,
                OnRecyclerViewItemClickListener itemClickListener) {
            super(binding.getRoot());
            mLinearBinding = binding;
            mItemClickListener = itemClickListener;
        }

        private ItemViewholder(ItemMovieGridBinding binding,
                OnRecyclerViewItemClickListener itemClickListener) {
            super(binding.getRoot());
            mGridBinding = binding;
            mItemClickListener = itemClickListener;
        }

        private void bind(Movie movie) {
            if (mIsGridLayout) {
                mGridBinding.setViewModel(
                        new ItemMovieViewModel(mContext, movie, mItemClickListener));
                mGridBinding.executePendingBindings();
            } else {
                mLinearBinding.setViewModel(
                        new ItemMovieViewModel(mContext, movie, mItemClickListener));
                mLinearBinding.executePendingBindings();
            }
        }
    }
}
