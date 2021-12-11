package com.youwu.qucanguibuhuo.ui.main.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;


import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.ui.commodity.CommodityListActivity;
import com.youwu.qucanguibuhuo.ui.main.guizi.CabinetListActivity;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 2021/11/24
 * 取餐柜列表Model
 */

public class TwoItemViewModel extends ItemViewModel<TwoViewModel> {
    public ObservableField<CabinetBean.OrderDataBean> entity = new ObservableField<>();
    public Drawable drawableImg;

    public TwoItemViewModel(@NonNull TwoViewModel viewModel, CabinetBean.OrderDataBean entity) {
        super(viewModel);
        this.entity.set(entity);
        //ImageView的占位图片，可以解决RecyclerView中图片错误问题
        drawableImg = ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.img_zhanweitu);
    }


    /**
     * 获取position的方式有很多种,indexOf是其中一种，常见的还有在Adapter中、ItemBinding.of回调里
     *
     * @return
     */
    public int getPosition() {
        return viewModel.getItemPosition(this);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //跳转到取餐柜列表
            Bundle bundle=new Bundle();
            bundle.putSerializable("store_id", entity.get().getId());
            viewModel.startActivity(CabinetListActivity.class,bundle);

        }
    });


}
