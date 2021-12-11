package com.youwu.qucanguibuhuo.ui.commodity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.google.android.material.tabs.TabLayout;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;

import com.youwu.qucanguibuhuo.databinding.ActivityCommodityListBinding;
import com.youwu.qucanguibuhuo.ui.base.adapter.BaseFragmentPagerAdapter;
import com.youwu.qucanguibuhuo.ui.commodity.fragment.MorningFragment;
import com.youwu.qucanguibuhuo.ui.commodity.fragment.AfternoonFragment;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 商品列表
 * 2021/11/24
 * 金库
 */

public class CommodityListActivity extends BaseActivity<ActivityCommodityListBinding, CommodityListViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new MorningFragment(1));
        list.add(new MorningFragment(2));

        return list;
    }


    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("早餐");
        list.add("中餐");

        return list;
    }

    @Override
    public CommodityListViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CommodityListViewModel.class);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_commodity_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
    @Override
    public void initData() {

        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为白色
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(true);

        mFragments = pagerFragment();
        titlePager = pagerTitleString();

        //设置TabLayout的模式
        binding.tabs.setTabMode(TabLayout.MODE_FIXED);
        //设置Adapter
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mFragments, titlePager);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }
}
