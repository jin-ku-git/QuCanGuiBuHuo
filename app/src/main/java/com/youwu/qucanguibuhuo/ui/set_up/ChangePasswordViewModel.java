package com.youwu.qucanguibuhuo.ui.set_up;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.youwu.qucanguibuhuo.app.AppApplication;
import com.youwu.qucanguibuhuo.bean.ChangePasswordBean;
import com.youwu.qucanguibuhuo.bean.SetUpBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.login.LoginActivity;
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
 * 修改密码页面
 */

public class ChangePasswordViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<ChangePasswordBean> change_password = new ObservableField<>();
    //使用LiveData
    public SingleLiveEvent<Integer> IntegerEvent = new SingleLiveEvent<>();
    //使用LiveData
    public ObservableField<Boolean> booleanEvent = new ObservableField<>();

    public ChangePasswordViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
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
            if (TextUtils.isEmpty(change_password.get().getUsed_password())) {
                ToastUtils.showShort("请输入原密码！");
                return;
            }
            if (TextUtils.isEmpty(change_password.get().getNew_password())) {
                ToastUtils.showShort("请输入新密码！");
                return;
            }
            if (TextUtils.isEmpty(change_password.get().getDetermine_new_password())) {
                ToastUtils.showShort("请输入确认新密码！");
                return;
            }
            if (booleanEvent.get()){
                String submitJson = new Gson().toJson(change_password.get());
//                RxToast.normal("提交的json数据：\n"+submitJson);
                updatePwd();

            }else {
                RxToast.normal("请与新密码保持一致");
            }

        }
    });

    /**
     * 修改密码
     */
    public void updatePwd(){
        model.UPDATE_PASSWORD(AppApplication.spUtils.getString("token"),change_password.get().getUsed_password(),change_password.get().getNew_password())
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
                            RxToast.normal("修改成功");
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


}
