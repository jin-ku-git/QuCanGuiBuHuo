package com.youwu.qucanguibuhuo.ui.main.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.youwu.qucanguibuhuo.databinding.FragmentTwoBinding;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 2021/11/22
 * 取餐柜列表
 * 金库
 */

public class TwoFragment extends BaseFragment<FragmentTwoBinding, TwoViewModel> {

    private int PAGE=1;//页数
    private int SIZE=10;//一页几条

    @Override
    public void initParam() {
        super.initParam();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_two;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public TwoViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(TwoViewModel.class);
    }

    @Override
    public void initData() {
        StatusBarUtil.setRootViewFitsSystemWindows(getActivity(), true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(getActivity());
        StatusBarUtil.setDarkMode(getActivity());//使状态栏字体变为黑色

        //测试数据
        viewModel.getList(PAGE,SIZE);

                //下拉刷新
        binding.smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.foodList.clear();
                //测试数据
                viewModel.getList(PAGE,SIZE);
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        binding.smartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PAGE++;
                viewModel.getList(PAGE,SIZE);
                refreshLayout.finishLoadMore(true);
            }
        });
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

    /**
     * 导航弹窗
     */
    private void initNavigation() {
        new XPopup.Builder(getContext())
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
