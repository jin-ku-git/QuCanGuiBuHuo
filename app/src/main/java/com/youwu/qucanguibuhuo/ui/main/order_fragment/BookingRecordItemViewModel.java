package com.youwu.qucanguibuhuo.ui.main.order_fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.ui.commodity.order_details.OrderDetailsActivity;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 2021/11/26
 * 待存货列表Model
 */

public class BookingRecordItemViewModel extends ItemViewModel<BookingRecordViewModel> {
    public ObservableField<OrderBean> entity = new ObservableField<>();
    public Drawable drawableImg;

    public BookingRecordItemViewModel(@NonNull BookingRecordViewModel viewModel, OrderBean entity) {
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

            //拿到position
            int position = viewModel.OrderList.indexOf(BookingRecordItemViewModel.this);
            //跳转到订单详情
            Bundle bundle=new Bundle();
            bundle.putSerializable("order", entity.get());
            bundle.putInt("order_type", 1);
            viewModel.startActivity(OrderDetailsActivity.class,bundle);

        }
    });


}
