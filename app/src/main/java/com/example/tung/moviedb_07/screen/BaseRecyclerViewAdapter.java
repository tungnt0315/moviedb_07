package com.example.tung.moviedb_07.screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tung on 6/8/17.
 * Base Adapter.
 *
 * @param <V> is a type extend from {@link RecyclerView.ViewHolder}
 */

public abstract class BaseRecyclerViewAdapter<V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    private final Context mContext;
    protected OnRecyclerViewItemClickListener mClickListener;

    protected BaseRecyclerViewAdapter(@NonNull Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    public void setClickListener(OnRecyclerViewItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    /**
     * OnRecyclerViewItemClickListener
     *
     * @param <T>
     */
    public interface OnRecyclerViewItemClickListener<T> {
        void onItemRecyclerViewClick(T item);
    }
}
