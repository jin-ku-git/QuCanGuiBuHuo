package com.youwu.qucanguibuhuo.ui.main;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.youwu.qucanguibuhuo.data.DemoRepository;


import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * Created by goldze on 2017/7/17.
 */

public class MainViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<Integer> IntegerEvent = new SingleLiveEvent<>();

    public MainViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //网络访问点击事件
    public BindingCommand netWorkClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    //首页点击事件
    public BindingCommand oneOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            IntegerEvent.setValue(1);
        }
    });
    //取餐柜
    public BindingCommand twoOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            IntegerEvent.setValue(2);
        }
    });


}
