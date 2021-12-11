package com.youwu.qucanguibuhuo.ui.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.cretin.www.cretinautoupdatelibrary.model.DownloadInfo;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppApplication;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.databinding.ActivityLoginBinding;
import com.youwu.qucanguibuhuo.ui.main.MainActivity;
import com.youwu.qucanguibuhuo.ui.set_up.UpData;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 一个MVVM模式的登陆界面
 * 2021/11/22
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uc.pSwitchEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if (viewModel.uc.pSwitchEvent.getValue()) {
                    KLog.d("测试11111");
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw);
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码不可见
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw_press);
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        viewModel.uc.checkBoxEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (binding.check.isChecked()){
                    viewModel.login();
                }else {
                    RxToast.warning("请阅读并同意《惠民早餐预约的用户协议注册协议》");
                }
            }
        });
        viewModel.uc.upDateEvent.observe(this, new Observer<UpDateBean>() {
            @Override
            public void onChanged(UpDateBean upDateBean) {
                UpData.UpData(upDateBean);
            }
        });
    }

    @Override
    public void initData() {
        //修改状态栏是状态栏透明
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为黑色

        /**
         * 检查更新
         */
        viewModel.getAppVersion();
        KLog.e("token:"+AppApplication.spUtils.getString("token"));

        if (AppApplication.spUtils.getString("token")!=null&&!"".equals(AppApplication.spUtils.getString("token"))){
            startActivity(MainActivity.class);
            finish();
        }

        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(false);

        //默认勾选
        binding.check.setChecked(true);



    }

}
