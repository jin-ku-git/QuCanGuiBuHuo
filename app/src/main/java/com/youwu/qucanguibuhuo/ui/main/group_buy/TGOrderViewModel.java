package com.youwu.qucanguibuhuo.ui.main.group_buy;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.sousuo.SouSuoActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * 2021/11/26
 * 早餐预约订单Model
 * 金库
 */

public class TGOrderViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Integer> typeEvent = new SingleLiveEvent<>();

    public TGOrderViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }



    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //门店选择点击事件
    public BindingCommand store_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(1);
        }
    });
    //柜子选择点击事件
    public BindingCommand cabinet_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(2);
        }
    });

    //搜索点击事件
    public BindingCommand SouSuoOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //跳转到搜索页面
            Bundle bundle=new Bundle();
            bundle.putInt("type", 2);
            startActivity(SouSuoActivity.class,bundle);
        }
    });


}
