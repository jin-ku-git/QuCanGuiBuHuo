package com.youwu.qucanguibuhuo.ui.main.scan_open;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityScanOpenBinding;
import com.youwu.qucanguibuhuo.ui.base.adapter.BaseFragmentPagerAdapter;
import com.youwu.qucanguibuhuo.ui.main.scan_open.fragment.GoodsFragment;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 2021/11/30
 * 开门补货/开门清货
 * 金库
 */

public class ScanOpenActivity extends BaseActivity<ActivityScanOpenBinding, ScanOpenViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    private int state_type;//0 开门补货 1 开门清货

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        state_type=getIntent().getIntExtra("state_type", 0);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_scan_open;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ScanOpenViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ScanOpenViewModel.class);
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initData() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为黑色
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(true);

        viewModel.state_type.set(state_type);

        mFragments = pagerFragment();
        titlePager = pagerTitleString();

        //设置TabLayout的模式
        binding.tabs.setTabMode(TabLayout.MODE_FIXED);
        //设置Adapter
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mFragments, titlePager);
        binding.viewPager.setAdapter(pagerAdapter);
        //防止内存溢出或泄露
        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }

    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new GoodsFragment("1",state_type));
        list.add(new GoodsFragment("2",state_type));


        return list;
    }


    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();

        list.add("早餐");
        list.add("午餐");

        return list;
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.d("销毁了");
    }


}
