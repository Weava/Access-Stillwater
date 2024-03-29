package com.teaman.accessstillwater.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weava on 3/14/16.
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected List<T> mElements;

    public BaseRecyclerAdapter() {
        mElements = new ArrayList<>();
    }

    public BaseRecyclerAdapter(List<T> elements) {
        mElements = elements;
    }

    public void add(T element) {
        mElements.add(element);
        notifyItemInserted(getItemCount() - 1);
    }

    public void add(List<T> elements) {
        mElements.addAll(elements);
        notifyDataSetChanged();
    }

    public void remove(T element) {
        mElements.remove(element);
        notifyItemRemoved(mElements.indexOf(element));
    }

    public void remove(int position) {
        mElements.remove(position);
        notifyItemRemoved(position);
    }

    public void swapData(List<T> elements) {
        mElements.clear();
        add(elements);
        notifyDataSetChanged();
    }

    public void clear() {
        mElements.clear();
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false);
        return inflateViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mElements.size();
    }

    public abstract @LayoutRes int getLayoutRes();

    public abstract VH inflateViewHolder(View v);
}
