package com.youwu.qucanguibuhuo.ui.main.myadmin;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.PreOrderBean;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 2021/11/26
 * 早餐套餐列表Model
 */

public class ZCLayoutItemViewModel extends ItemViewModel<ZCLayoutViewModel> {
    public ObservableField<PreOrderBean.PreOrder> entity = new ObservableField<>();
    public Drawable drawableImg;

    public ZCLayoutItemViewModel(@NonNull ZCLayoutViewModel viewModel, PreOrderBean.PreOrder entity) {
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
            int position = viewModel.PreOrderList.indexOf(ZCLayoutItemViewModel.this);

            RxToast.normal(entity.get().getName());




        }
    });


}
