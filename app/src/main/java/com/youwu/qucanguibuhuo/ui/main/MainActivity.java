package com.youwu.qucanguibuhuo.ui.main;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityMainBinding;
import com.youwu.qucanguibuhuo.ui.login.LoginViewModel;
import com.youwu.qucanguibuhuo.ui.main.fragment.OneFragment;
import com.youwu.qucanguibuhuo.ui.main.fragment.TwoFragment;
import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 2021/11/22
 * 首页
 * 金库
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    private OneFragment mOneFragment;
    private TwoFragment mTowFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public MainViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(false);
        setSwPage(1);
    }

    @Override
    public void initViewObservable() {

        //注册文件下载的监听
        viewModel.IntegerEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1:
                        binding.oneHome.setonSelected(true);
                        binding.twoHome.setonSelected(false);
                        setSwPage(1);
                        break;
                    case 2:
                        binding.twoHome.setonSelected(true);
                        binding.oneHome.setonSelected(false);
                        setSwPage(2);
                        break;

                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    public void setSwPage(int i) {

        //获取FragmentManager对象
        manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            case 1:
                if (mOneFragment == null) {
                    mOneFragment = new OneFragment();
                    transaction.add(R.id.frame, mOneFragment);
                } else {
                    //对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mOneFragment);
                }
                break;
            case 2:
                if (mTowFragment == null) {
                    mTowFragment = new TwoFragment();
                    transaction.add(R.id.frame, mTowFragment);
                } else {
                    //对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mTowFragment);
                }
                break;
        }
        transaction.commit();
    }

    //将全部Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mOneFragment != null) {
            transaction.hide(mOneFragment);
        }
        if (mTowFragment != null) {
            transaction.hide(mTowFragment);
        }

    }
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示

                View toastRoot = getLayoutInflater().inflate(R.layout.my_toast, null);
                Toast toast = new Toast(this);
                toast.setView(toastRoot);
                TextView tv = (TextView) toastRoot.findViewById(R.id.TextViewInfo);
                tv.setText("再按一次退出程序");
                toast.setGravity(Gravity.BOTTOM, 0, 150);
                toast.show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
