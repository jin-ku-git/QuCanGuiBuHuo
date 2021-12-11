package com.youwu.qucanguibuhuo.ui.main.group_buy;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityCHDetailsBinding;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/27
 * 存货详情
 * 金库
 */

public class CHDetailsActivity extends BaseActivity<ActivityCHDetailsBinding, CHDetailsViewModel> {

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_c_h_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public CHDetailsViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CHDetailsViewModel.class);
    }

    @Override
    public void initViewObservable() {

        //注册文件下载的监听
        viewModel.loadUrlEvent.observe(this, new Observer<String>() {
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
//        viewModel.getList();

        //下拉刷新
        binding.smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                viewModel.PreOrderList.clear();
                //获取测试数据
                viewModel.getList();
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        binding.smartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                RxToast.normal("上拉加载");
                refreshLayout.finishLoadMore(true);
            }
        });

    }



}
