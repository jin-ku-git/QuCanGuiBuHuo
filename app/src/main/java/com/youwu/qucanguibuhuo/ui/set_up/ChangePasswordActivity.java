package com.youwu.qucanguibuhuo.ui.set_up;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.ChangePasswordBean;
import com.youwu.qucanguibuhuo.databinding.ActivityChangePasswordBinding;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/24
 * 修改密码页面
 * 金库
 */

public class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel> {

    ChangePasswordBean changePasswordBean=new ChangePasswordBean();
    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public ChangePasswordViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ChangePasswordViewModel.class);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_password;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为黑色
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(true);

        viewModel.change_password.set(changePasswordBean);
        viewModel.booleanEvent.set(true);

        binding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (viewModel.change_password.get().getDetermine_new_password().equals(viewModel.change_password.get().getNew_password())){
                    viewModel.booleanEvent.set(true);
                }else {
                    viewModel.booleanEvent.set(false);
                }

            }
        });
    }

    @Override
    public void initViewObservable() {

    }



}
