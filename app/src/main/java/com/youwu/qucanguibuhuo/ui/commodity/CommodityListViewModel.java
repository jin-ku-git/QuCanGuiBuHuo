package com.youwu.qucanguibuhuo.ui.commodity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;


/**
 * 2021/11/24
 * 金库
 */

public class CommodityListViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<UserBean> user = new ObservableField<>();
    //下拉刷新完成
    public SingleLiveEvent finishRefreshing = new SingleLiveEvent<>();

    public CommodityListViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("下拉刷新");

        }
    });

    //返回
    public BindingCommand finishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           finish();

        }
    });


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
