package com.youwu.qucanguibuhuo.ui.main.scan_open;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.sousuo.SouSuoActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * 2021/11/30
 * 开门补货/开门清货Model
 * 金库
 */

public class ScanOpenViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<Integer> state_type = new ObservableField<>();
    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public ScanOpenViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }



    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

}
