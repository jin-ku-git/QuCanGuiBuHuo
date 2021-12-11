package com.youwu.qucanguibuhuo.ui.commodity.order_reserve;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityOrderReserveBinding;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/26
 * 预约订单
 * 金库
 */

public class OrderReserveActivity extends BaseActivity<ActivityOrderReserveBinding, OrderReserveViewModel> {

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_order_reserve;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public OrderReserveViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(OrderReserveViewModel.class);
    }

    @Override
    public void initViewObservable() {

        //共多少预约数量
        viewModel.order_numEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String url) {

            }
        });
    }

    @Override
    public void initData() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为黑色
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(true);
        //获取测试数据
        viewModel.getList();



    }



}
