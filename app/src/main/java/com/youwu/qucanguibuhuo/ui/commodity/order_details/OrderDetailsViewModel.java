package com.youwu.qucanguibuhuo.ui.commodity.order_details;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * 2021/11/26
 * 订单详情Model
 * 金库
 */

public class OrderDetailsViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<OrderBean> order = new ObservableField<>();
    public ObservableField<Integer> order_type = new ObservableField<>();
    //使用LiveData
    public SingleLiveEvent<Integer> TypeEvent = new SingleLiveEvent<>();

    public OrderDetailsViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }
    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    //一键退款点击事件
    public BindingCommand refundOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            TypeEvent.setValue(1);
        }
    });
    //打电话点击事件
    public BindingCommand phoneOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxToast.error("打电话");
            showPhone("123456");
        }
    });
}
