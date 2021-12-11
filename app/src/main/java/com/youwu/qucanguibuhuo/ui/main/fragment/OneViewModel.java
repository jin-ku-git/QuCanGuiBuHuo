package com.youwu.qucanguibuhuo.ui.main.fragment;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.youwu.qucanguibuhuo.app.AppApplication;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.login.LoginActivity;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHRecordActivity;
import com.youwu.qucanguibuhuo.ui.main.group_buy.TGOrderActivity;
import com.youwu.qucanguibuhuo.ui.main.historical_data.BHRecordActivity;
import com.youwu.qucanguibuhuo.ui.main.historical_data.QHRecordActivity;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCLayoutActivity;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCOrderActivity;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCSummaryActivity;
import com.youwu.qucanguibuhuo.ui.set_up.SetUpActivity;
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
import static me.goldze.mvvmhabit.base.BaseActivity.finishAllActivity;


/**
 * 2021/11/24
 * 金库
 */

public class OneViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<UserBean> user = new ObservableField<>();
    //扫描二维码
    public SingleLiveEvent<Integer> passEvent = new SingleLiveEvent<>();
    //我的管理
    public SingleLiveEvent<Integer> GLEvent = new SingleLiveEvent<>();

    public OneViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }


    //设置
    public BindingCommand SetUpOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            startActivity(SetUpActivity.class);
        }
    });
    //扫码补货
    public BindingCommand sweepOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            passEvent.setValue(1);
        }
    });
    //团购存货
    public BindingCommand groupOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            passEvent.setValue(2);
        }
    });
    //早餐预约汇总
    public BindingCommand SummaryOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ZCSummaryActivity.class);
        }
    });
    //早餐预约分布
    public BindingCommand LayoutOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ZCLayoutActivity.class);
        }
    });
    //早餐预约订单
    public BindingCommand OrderOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ZCOrderActivity.class);
        }
    });
    //取货记录
    public BindingCommand QHOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(QHRecordActivity.class);
        }
    });
    //补货记录
    public BindingCommand BHOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle=new Bundle();
            bundle.putInt("type", 1);
            startActivity(BHRecordActivity.class,bundle);
        }
    });
    //补货记录
    public BindingCommand QingHOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle=new Bundle();
            bundle.putInt("type", 2);
            startActivity(BHRecordActivity.class,bundle);
        }
    });
    //团购订单
    public BindingCommand TGOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(TGOrderActivity.class);
        }
    });
    //存货记录
    public BindingCommand CHOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CHRecordActivity.class);
        }
    });

    /**
     * 获取用户信息
     */
    public void getUser() {
        model.GET_USER(AppApplication.spUtils.getString("token"))
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
                            UserBean userBean = JSON.parseObject(toPrettyFormat(submitJson), UserBean.class);
                            user.set(userBean);
                        }else if (403==response.code){
                            RxToast.normal(response.getMessage());
                            finishAllActivity();
                            AppApplication.spUtils.put("token","");
                            startActivity(LoginActivity.class);
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




    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
