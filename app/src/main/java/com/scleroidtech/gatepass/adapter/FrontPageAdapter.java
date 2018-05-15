package com.scleroidtech.gatepass.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scleroidtech.gatepass.R;
import com.scleroidtech.gatepass.model.tempModels.Front_View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Copyright (C) 3/13/18
 * Author ganesh
 */
@Deprecated
public class FrontPageAdapter extends Adapter<FrontPageAdapter.ViewHolder> {
    private List<Front_View> front_views;

    public FrontPageAdapter(List<Front_View> front_views) {
        this.front_views = front_views;
    }

    public void setFront_views(List<Front_View> front_views) {
        this.front_views = front_views;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewDataBinding inflate = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_front_page, parent, false);
        return new ViewHolder(inflate.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(front_views.get(position));
    }


    @Override
    public int getItemCount() {
        return front_views.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_home_page)
        ImageView frontImage;
        @BindView(R.id.textView_home_page_title)
        TextView frontPageTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData(Front_View data) {
            frontImage.setImageResource(data.getActionImageDrawable());
            frontPageTitle.setText(data.getTitle());
        }
    }
}
