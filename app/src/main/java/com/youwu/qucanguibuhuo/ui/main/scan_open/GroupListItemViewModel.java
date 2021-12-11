package com.youwu.qucanguibuhuo.ui.main.scan_open;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHDetailsViewModel;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHOrderDetailsActivity;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 2021/12/01
 * 团购存货item
 */

public class GroupListItemViewModel extends ItemViewModel<GroupListViewModel> {
    public ObservableField<OrderBean> entity = new ObservableField<>();
    public Drawable drawableImg;

    public GroupListItemViewModel(@NonNull GroupListViewModel viewModel, OrderBean entity) {
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
            int position = viewModel.TGOrderList.indexOf(GroupListItemViewModel.this);

        }
    });

    //移除的点击事件
    public BindingCommand deleteOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            //拿到position
            int position = viewModel.TGOrderList.indexOf(GroupListItemViewModel.this);
            viewModel.TGOrderList.remove(getPosition());

        }
    });


}
