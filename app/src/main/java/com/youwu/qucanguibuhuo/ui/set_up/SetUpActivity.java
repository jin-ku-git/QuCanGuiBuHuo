package com.youwu.qucanguibuhuo.ui.set_up;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cretin.www.cretinautoupdatelibrary.model.DownloadInfo;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.databinding.ActivitySetUpBinding;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.KLog;
import skin.support.SkinCompatManager;

/**
 * 2021/11/24
 * 设置页面
 * 金库
 */

public class SetUpActivity extends BaseActivity<ActivitySetUpBinding, SetUpViewModel>implements CompoundButton.OnCheckedChangeListener {


    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public SetUpViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SetUpViewModel.class);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_set_up;
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

        binding.version.setText("V"+getVersion());

        binding.sbDefault.setOnCheckedChangeListener(this);

    }

    @Override
    public void initViewObservable() {

        viewModel.upDateEvent.observe(this, new Observer<UpDateBean>() {
            @Override
            public void onChanged(UpDateBean upDateBean) {
                /**
                 * 检查更新
                 */
                UpData.UpData(upDateBean);
            }
        });

        viewModel.stateEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String aBoolean) {
                if ("1".equals(aBoolean)){
                    binding.sbDefault.setChecked(true);
                }else {
                    binding.sbDefault.setChecked(false);
                }

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        KLog.d("isChecked:"+isChecked);
        KLog.d("buttonView:"+buttonView);

        if (isChecked){
            viewModel.setState("1");
        }else {
            viewModel.setState("2");
        }


        if (isChecked){
            SkinCompatManager.getInstance().loadSkin("night", null, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
        }else {
            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
    }
}
