package com.youwu.qucanguibuhuo.ui.set_up;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.youwu.qucanguibuhuo.bean.SetUpBean;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseBean;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

import static com.youwu.qucanguibuhuo.app.AppApplication.toPrettyFormat;

/**
 * 2021/11/24
 * 设置页面
 */

public class SetUpViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<SetUpBean> set_up = new ObservableField<>();
    //使用LiveData
    public SingleLiveEvent<Integer> IntegerEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> stateEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<UpDateBean> upDateEvent = new SingleLiveEvent<>();

    public SetUpViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);

        stateEvent.setValue(model.getSwitch());
    }

    public void setState(String state){
        model.saveSwitch(state);
    }

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    //修改密码点击事件
    public BindingCommand modifyOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangePasswordActivity.class);
        }
    });
    //检查更新点击事件
    public BindingCommand updatesOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            IntegerEvent.setValue(1);
            RxToast.normal("检查更新！");
        }
    });
    //退出登录点击事件
    public BindingCommand signOutOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxToast.normal("退出登录");
        }
    });


    /**
     * 检查更新
     **/
    public void getAppVersion() {

        model.GET_APP_VERSION()
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new DisposableObserver<BaseBean<Object>>() {
                    @Override
                    public void onNext(BaseBean response) {
                        String submitJson = new Gson().toJson(response.data);


                        if (0==response.getCode()){
                            UpDateBean upDateBean = JSON.parseObject(toPrettyFormat(submitJson), UpDateBean.class);
                            upDateEvent.setValue(upDateBean);
                        }else {
                            RxToast.normal(response.getMessage());
                        }
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        //关闭对话框
                        dismissDialog();
                        if (throwable instanceof ResponseThrowable) {
                            ToastUtils.showShort(((ResponseThrowable) throwable).message);
                        }
                    }
                    @Override
                    public void onComplete() {
                        //关闭对话框
                        dismissDialog();
                    }
                });
    }


}
