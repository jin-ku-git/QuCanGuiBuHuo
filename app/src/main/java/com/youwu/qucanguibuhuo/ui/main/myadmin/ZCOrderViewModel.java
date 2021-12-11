package com.youwu.qucanguibuhuo.ui.main.myadmin;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.PreOrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.sousuo.SouSuoActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/26
 * 早餐预约订单Model
 * 金库
 */

public class ZCOrderViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Integer> typeEvent = new SingleLiveEvent<>();

    public ZCOrderViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }



    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //订单选择点击事件
    public BindingCommand order_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(1);
        }
    });
    //门店选择点击事件
    public BindingCommand store_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(2);
        }
    });
    //柜子选择点击事件
    public BindingCommand cabinet_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(3);
        }
    });

    //搜索点击事件
    public BindingCommand SouSuoOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //跳转到搜索页面
            Bundle bundle=new Bundle();
            bundle.putInt("type", 1);
            startActivity(SouSuoActivity.class,bundle);
        }
    });



}
