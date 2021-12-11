package com.youwu.qucanguibuhuo.ui.main.group_buy;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lxj.xpopup.XPopup;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.OrderBean;

import com.youwu.qucanguibuhuo.databinding.ActivityCHOrderDetailsBinding;
import com.youwu.qucanguibuhuo.ui.main.refund.GroupCancleOrderPop;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/27
 * 存货订单详情
 * 金库
 */

public class CHOrderDetailsActivity extends BaseActivity<ActivityCHOrderDetailsBinding, CHOrderDetailsViewModel> {


    private OrderBean orderBean;

    private Context mContext;

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //获取列表传入的实体
        orderBean = (OrderBean) getIntent().getSerializableExtra("order");

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_c_h_order_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public CHOrderDetailsViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CHOrderDetailsViewModel.class);
    }

    @Override
    public void initViewObservable() {

        //注册文件下载的监听
        viewModel.loadUrlEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1://申请退款
                        GroupCancleOrderPop pop=new  GroupCancleOrderPop(mContext);
                        pop.setSubCallBack(new GroupCancleOrderPop.SubCallBack() {
                            @Override
                            public void sub(boolean rollback_stock, String reason) {
                                RxToast.normal("是否回滚库存"+rollback_stock+"原因："+reason);
                            }
                        });

                   new  XPopup.Builder(mContext)
                            .asCustom(pop)
                            .show();
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        mContext=this;
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为黑色
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(true);

        if (orderBean!=null){
            viewModel.order.set(orderBean);
        }

        viewModel.getList();




    }

}
