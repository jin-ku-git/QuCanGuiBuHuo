package com.youwu.qucanguibuhuo.ui.main.myadmin;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lxj.xpopup.XPopup;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;

import com.youwu.qucanguibuhuo.databinding.ActivityZCSummaryBinding;
import com.youwu.qucanguibuhuo.ui.main.myadmin.popwindow.MDPopupView;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;


import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/25
 * 早餐预约汇总
 * 金库
 */

public class ZCSummaryActivity extends BaseActivity<ActivityZCSummaryBinding, ZCSummaryViewModel> {

    private MDPopupView popupView;

    ArrayList<String> list;
    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_z_c_summary;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ZCSummaryViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ZCSummaryViewModel.class);
    }

    @Override
    public void initViewObservable() {

        //门店选择的监听
        viewModel.TypeEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1:
                        showPartShadow(binding.tvAll);
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

        //获取测试数据
        viewModel.getList();
        list = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            list.add("门店" + (i + 1));
        }
        binding.tvAll.setText(list.get(0));


    }

    private void showPartShadow(final View v){
        if(popupView==null){
            popupView = (MDPopupView) new XPopup.Builder(this)
                    .atView(v)
                    .asCustom(new MDPopupView(this));
        }


        popupView.setItemsData(list);
        popupView.setOnChoiceener(new MDPopupView.OnChoiceener() {
            @Override
            public void onChoice(String data) {
                binding.tvAll.setText(data);
                popupView.dismiss();
            }
        });

        popupView.show();

    }



}
