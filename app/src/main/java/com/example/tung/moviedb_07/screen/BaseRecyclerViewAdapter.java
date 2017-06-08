package com.example.tung.moviedb_07.screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

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
     */
    public interface OnRecyclerViewItemClickListener<T> {
        void onItemRecyclerViewClick(T item);
    }
}
