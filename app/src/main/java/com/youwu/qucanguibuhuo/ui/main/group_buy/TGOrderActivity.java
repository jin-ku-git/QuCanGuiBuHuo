package com.youwu.qucanguibuhuo.ui.main.group_buy;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityTGOrderBinding;
import com.youwu.qucanguibuhuo.ui.base.adapter.BaseFragmentPagerAdapter;
import com.youwu.qucanguibuhuo.ui.main.myadmin.popwindow.MDPopupView;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryFragment;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;


import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 2021/11/27
 * 团购订单
 * 金库
 */

public class TGOrderActivity extends BaseActivity<ActivityTGOrderBinding, TGOrderViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    private MDPopupView popupView;
    private MDPopupView popupView_cabinet;
    ArrayList<String> list_store;//门店
    ArrayList<String> list_cabinet;//柜子

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_t_g_order;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public TGOrderViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(TGOrderViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //门店选择的监听
        viewModel.typeEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1://选择门店
                        showStore(binding.tvStore);
                        break;
                    case 2://选择柜子
                        showCabinet(binding.tvCabinet);
                        break;
                }
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


        list_store = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            list_store.add("门店" + (i + 1));
        }
        binding.tvStore.setText(list_store.get(0));
        list_cabinet = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            list_cabinet.add("柜子" + (i + 1));
        }
        binding.tvCabinet.setText(list_cabinet.get(0));


        mFragments = pagerFragment();
        titlePager = pagerTitleString();

        //设置TabLayout的模式
        binding.tabs.setTabMode(TabLayout.MODE_FIXED);
        //设置Adapter
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mFragments, titlePager);
        binding.viewPager.setAdapter(pagerAdapter);
        //防止内存溢出或泄露
        binding.viewPager.setOffscreenPageLimit(3);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }

    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new InventoryFragment("1",2));
        list.add(new InventoryFragment("2",2));
        list.add(new InventoryFragment("3",2));

        return list;
    }


    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("待存货");
        list.add("待取货");
        list.add("已完成");

        return list;
    }

    /**
     * 门店弹窗
     * @param v
     */
    private void showStore(final View v){
        if(popupView==null){
            popupView = (MDPopupView) new XPopup.Builder(this)
                    .atView(v)
                    .asCustom(new MDPopupView(this));
        }
        popupView.setItemsData(list_store);
        popupView.setOnChoiceener(new MDPopupView.OnChoiceener() {
            @Override
            public void onChoice(String data) {
                binding.tvStore.setText(data);
                popupView.dismiss();
            }
        });
        popupView.show();
    }
    /**
     * 柜子弹窗
     * @param v
     */
    private void showCabinet(final View v){
        if(popupView_cabinet==null){
            popupView_cabinet = (MDPopupView) new XPopup.Builder(this)
                    .atView(v)
                    .asCustom(new MDPopupView(this));
        }
        popupView_cabinet.setItemsData(list_cabinet);
        popupView_cabinet.setOnChoiceener(new MDPopupView.OnChoiceener() {
            @Override
            public void onChoice(String data) {
                binding.tvCabinet.setText(data);
                popupView_cabinet.dismiss();
            }
        });
        popupView_cabinet.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.d("销毁了");
    }


}
