package com.youwu.qucanguibuhuo.ui.main.group_buy;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityCHRecordBinding;
import com.youwu.qucanguibuhuo.ui.main.myadmin.popwindow.MDPopupView;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;


import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/27
 * 存货记录
 * 金库
 */

public class CHRecordActivity extends BaseActivity<ActivityCHRecordBinding, CHRecordViewModel> {

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
        return R.layout.activity_c_h_record;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public CHRecordViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CHRecordViewModel.class);
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


        //获取测试数据
//        viewModel.getList();

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


}
