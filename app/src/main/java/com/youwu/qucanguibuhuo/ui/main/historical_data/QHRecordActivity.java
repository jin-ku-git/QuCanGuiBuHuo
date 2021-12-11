package com.youwu.qucanguibuhuo.ui.main.historical_data;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityQHRecordBinding;
import com.youwu.qucanguibuhuo.ui.base.adapter.BaseFragmentPagerAdapter;
import com.youwu.qucanguibuhuo.ui.main.myadmin.popwindow.MDPopupView;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.BookingRecordFragment;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;


import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/27
 * 取货记录
 * 金库
 */

public class QHRecordActivity extends BaseActivity<ActivityQHRecordBinding, QHRecordViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    private MDPopupView popupView_order;
    private MDPopupView popupView_store;
    private MDPopupView popupView_cabinet;

    ArrayList<String> list_order;//订单
    ArrayList<String> list_store;//门店
    ArrayList<String> list_cabinet;//柜子

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_q_h_record;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public QHRecordViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(QHRecordViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //门店选择的监听
        viewModel.typeEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1:
                        showOrder(binding.tvOrder);
                        break;
                    case 2:
                        showStore(binding.tvStore);
                        break;
                    case 3:
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

        list_order = new ArrayList<String>();
        for (int i = 1; i < 6; i++) {
            list_order.add("订单" + (i));
        }
        binding.tvOrder.setText(list_order.get(0));

        list_store= new ArrayList<String>();
        for (int i = 1; i < 6; i++) {
            list_store.add("门店" + (i));
        }
        binding.tvStore.setText(list_store.get(0));
        list_cabinet = new ArrayList<String>();
        for (int i = 1; i < 6; i++) {
            list_cabinet.add("柜子" + (i));
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
        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }

    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new BookingRecordFragment("1"));
        list.add(new BookingRecordFragment("2"));

        return list;
    }


    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("预约");
        list.add("团购");

        return list;
    }

    /**
     * 订单下拉框
     * @param v
     */
    private void showOrder(final View v){
        if(popupView_order==null){
            popupView_order = (MDPopupView) new XPopup.Builder(this)
                    .atView(v)
                    .asCustom(new MDPopupView(this));
            popupView_order.setItemsData(list_order);
        }
        popupView_order.setOnChoiceener(new MDPopupView.OnChoiceener() {
            @Override
            public void onChoice(String data) {
                binding.tvOrder.setText(data);
                popupView_order.dismiss();
            }
        });
        popupView_order.show();
    }

    /**
     * 门店下拉框
     * @param v
     */
    private void showStore(final View v){
        if(popupView_store==null){
            popupView_store = (MDPopupView) new XPopup.Builder(this)
                    .atView(v)
                    .asCustom(new MDPopupView(this));
            popupView_store.setItemsData(list_store);
        }
        popupView_store.setOnChoiceener(new MDPopupView.OnChoiceener() {
            @Override
            public void onChoice(String data) {
                binding.tvStore.setText(data);
                popupView_store.dismiss();
            }
        });
        popupView_store.show();
    }
    /**
     * 柜子下拉框
     * @param v
     */
    private void showCabinet(final View v){
        if(popupView_cabinet==null){
            popupView_cabinet = (MDPopupView) new XPopup.Builder(this)
                    .atView(v)
                    .asCustom(new MDPopupView(this));
            popupView_cabinet.setItemsData(list_cabinet);
        }
        popupView_cabinet.setOnChoiceener(new MDPopupView.OnChoiceener() {
            @Override
            public void onChoice(String data) {
                binding.tvCabinet.setText(data);
                popupView_cabinet.dismiss();
            }
        });
        popupView_cabinet.show();
    }

}
