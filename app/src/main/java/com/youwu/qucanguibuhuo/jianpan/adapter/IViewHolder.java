package com.youwu.qucanguibuhuo.jianpan.adapter;

import android.graphics.Paint;
import androidx.annotation.IdRes;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import com.youwu.qucanguibuhuo.R;


public class IViewHolder extends BaseViewHolder {
    private RequestOptions options = new RequestOptions()
            .error(R.mipmap.img_goods_default)
            .centerCrop()
            .placeholder(R.mipmap.img_goods_default);

    public IViewHolder(View view) {
        super(view);
    }

    public IViewHolder setImage(@IdRes int viewId, Object url) {
        ImageView view = this.getView(viewId);
        Glide.with(view)
                .asBitmap()
                .apply(options)
                .load(url)
                .into(view);
        return this;
    }
    public IViewHolder setImageRound(@IdRes int viewId, Object url) {
        ImageView view = this.getView(viewId);


        return this;
    }
    public IViewHolder setTvCenterLine(@IdRes int viewId) {
        TextView textView = this.getView(viewId);
        textView.getPaint().setAntiAlias(true);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        return this;
    }

    /**
     *新版本已经移除了了改方法
     * @param viewId
     * @param checked
     * @return
     */
    @Deprecated
    public IViewHolder setChecked(@IdRes int viewId, boolean checked) {
        View view = getView(viewId);
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }


}
