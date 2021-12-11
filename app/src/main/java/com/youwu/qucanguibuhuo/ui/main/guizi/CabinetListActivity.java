package com.youwu.qucanguibuhuo.ui.main.guizi;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.databinding.ActivityCabinetListBinding;
import com.youwu.qucanguibuhuo.ui.main.myadmin.popwindow.MDPopupView;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/12/10
 * 取餐柜列表
 * 金库
 */

public class CabinetListActivity extends BaseActivity<ActivityCabinetListBinding, CabinetListViewModel> {

    private String store_id;//门店id
    private int PAGE=1;
    private int SIZE=10;

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //获取列表传入的id
        store_id = getIntent().getStringExtra("store_id");
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cabinet_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public CabinetListViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CabinetListViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //导航弹窗
        viewModel.DHEvent.observe(this, new Observer<CabinetBean.OrderDataBean>() {
            @Override
            public void onChanged(CabinetBean.OrderDataBean orderDataBean) {
                initNavigation();
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

        //测试数据
        viewModel.getList(store_id,PAGE,SIZE);

        //下拉刷新
        binding.smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.foodList.clear();
                //测试数据
                viewModel.getList(store_id,PAGE,SIZE);
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        binding.smartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PAGE++;
                viewModel.getList(store_id,PAGE,SIZE);
                refreshLayout.finishLoadMore(true);
            }
        });


    }


    /**
     * 导航弹窗
     */
    private void initNavigation() {
        new XPopup.Builder(this)
                .asCenterList("请选择一项", new String[]{"高德地图", "百度地图", "腾讯地图"},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                RxToast.normal("选择的："+text);

                            }
                        })
                .show();
    }



}
