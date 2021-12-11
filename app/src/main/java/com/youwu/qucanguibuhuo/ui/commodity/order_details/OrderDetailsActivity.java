package com.youwu.qucanguibuhuo.ui.commodity.order_details;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ScreenUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.databinding.ActivityOrderDetailsBinding;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.goldze.mvvmhabit.base.BaseActivity;

import static com.blankj.utilcode.util.SnackbarUtils.dismiss;

/**
 * 2021/11/26
 * 订单详情
 * 金库
 */

public class OrderDetailsActivity extends BaseActivity<ActivityOrderDetailsBinding, OrderDetailsViewModel> {


    private OrderBean orderBean;
    private int order_type;

    private Context mContext;
    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //获取列表传入的实体
        orderBean = (OrderBean) getIntent().getSerializableExtra("order");

        order_type=getIntent().getIntExtra("order_type", 0);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_order_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public OrderDetailsViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(OrderDetailsViewModel.class);
    }

    @Override
    public void initViewObservable() {

        //注册文件下载的监听
        viewModel.TypeEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1://一键退款
                       new XPopup.Builder(mContext)
                               .maxWidth((int) (ScreenUtils.getAppScreenWidth() * 0.7))
                               .asConfirm("退款", "确认退款吗？",
                                       new OnConfirmListener() {
                                           @Override
                                           public void onConfirm() {
                                               RxToast.normal("退款");
                                           }
                                       })
                               .bindLayout(R.layout.my_confim_popup) //绑定已有布局
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
        //0是加入 1是购物车进来
        viewModel.order_type.set(order_type);

        String start_time="2021-11-27 13:27:50";
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end_time = dateFormat.format(date);


//        if (order_type==1){
//            if (getTimeCompareSize(start_time,end_time)==1){
//                binding.cdvCountDown.initEndTime(start_time)
//                        .calcTime()
//                        .startRun(1)
//                        .setCountDownEndListener(new CountDownView.CountDownEndListener() {
//                            @Override
//                            public void onCountDownEnd() {
//                                binding.cdvCountDown.setTvHourText("00")
//                                        .setTvMinuteText("00")
//                                        .setTvMinuteText("00");
//                            }
//                        });
//            }else {
//                binding.cdvCountDown.initEndTime(start_time)
//                        .calcTime_two()
//                        .startRun(2)
//                        .setCountDownEndListener(new CountDownView.CountDownEndListener() {
//                            @Override
//                            public void onCountDownEnd() {
//                                binding.cdvCountDown.setTvHourText("00")
//                                        .setTvMinuteText("00")
//                                        .setTvMinuteText("00");
//                            }
//                        });
//                binding.textTishi.setText("您已超时" + binding.cdvCountDown.getmDay() + "天");
//            }
//
//
//            binding.cdvCountDown.setOnTimeEnd(new CountDownView.OnTimeEnd() {
//                @Override
//                public void onTimeEndt() {
//                    binding.cdvCountDown.initEndTime(start_time)
//                            .calcTime_two()
//                            .startRun(2)
//                            .setCountDownEndListener(new CountDownView.CountDownEndListener() {
//                                @Override
//                                public void onCountDownEnd() {
//                                    binding.cdvCountDown.setTvHourText("00")
//                                            .setTvMinuteText("00")
//                                            .setTvMinuteText("00");
//                                }
//                            });
//                    binding.textTishi.setText("您已超时" + binding.cdvCountDown.getmDay() + "天");
//                }
//            });
//        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (order_type==1){
//            KLog.d("倒计时停止了");
//            binding.cdvCountDown.cancel();
//        }

    }
}
