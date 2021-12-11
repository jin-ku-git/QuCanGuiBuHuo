package com.youwu.qucanguibuhuo.jianpan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.youwu.qucanguibuhuo.R;


import java.util.List;

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, IViewHolder> implements LoadMoreModule {

    protected int skeletonLayout=-1;
    protected int skeletonCount = 2;
    protected boolean skeletonIsShow = false;
    private RecyclerViewSkeletonScreen skeletonScreen;

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    private OnReloadListener listener;


    public void showEmptyView() {
        notifyDataSetChanged();
        setEmptyView(setDefaultEmptyView((ViewGroup) getRecyclerView().getParent()));
    }

    public void showErrorView() {
        notifyDataSetChanged();
        View view = setDefaultError((ViewGroup) getRecyclerView().getParent());
        view.findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onReload();
                }
            }
        });
        setEmptyView(view);
    }
    public void showErrorView(String text) {
        notifyDataSetChanged();
        View view = setDefaultError((ViewGroup) getRecyclerView().getParent());
        view.findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onReload();
                }
            }
        });
        TextView textView=view.findViewById(R.id.tvMessge);
        textView.setText(text);
        setEmptyView(view);
    }

    public void showLoading() {
        notifyDataSetChanged();
        setEmptyView(setDefaultLoadingView((ViewGroup) getRecyclerView().getParent()));
    }

    /**
     * 展示骨架屏
     * 在show之前请先设置layout和 绑定recyclerview
     */
    public void showSkeleton() {
        if (skeletonLayout <= 0) {
            throw new NullPointerException("请先设置 skeletonLayout");
        }
        if (skeletonScreen == null) {
            skeletonScreen = Skeleton.bind(getRecyclerView())
                    .adapter(this)
                    .count(skeletonCount)
                    .color(R.color.shimmerColor)
                    .load(skeletonLayout)
                    .show();
        } else {
            skeletonScreen.show();
        }
        skeletonIsShow = true;
    }

    public void hideSkeleton() {
        if (skeletonScreen != null && skeletonIsShow) {
            skeletonScreen.hide();
            skeletonIsShow = false;
        }
    }

    public void setSkeletonCount(int skeletonCount) {
        this.skeletonCount = skeletonCount;
    }

    public void setListener(OnReloadListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    /**
     * 错误界面不允许重写
     *
     * @param parent
     * @return
     */
    private final View setDefaultError(ViewGroup parent) {

        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_error, parent, false);
    }

    /**
     * 重写 可改变空数据界面
     *
     * @param parent
     * @return
     */
    public View setDefaultEmptyView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty, parent, false);
    }

    public View setDefaultLoadingView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading, parent, false);
    }

    public RecyclerView getReView() {
        return getRecyclerView();
    }


    public void setSkeletonLayout(int layout) {
        this.skeletonLayout = layout;
    }

    @Deprecated
    public void bindToRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
    }

    public interface OnReloadListener {
        void onReload();
    }

    public String getRMB() {
        return getContext().getResources().getString(R.string.rmb);
    }
}
