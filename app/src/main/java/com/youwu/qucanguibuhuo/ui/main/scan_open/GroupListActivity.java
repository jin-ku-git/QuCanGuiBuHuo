package com.youwu.qucanguibuhuo.ui.main.scan_open;


import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.ActivityGroutListBinding;
import com.youwu.qucanguibuhuo.scanning.activity.CaptureActivity;
import com.youwu.qucanguibuhuo.utils.Constant;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 2021/12/01
 * 团购存货
 * 金库
 */

public class GroupListActivity extends BaseActivity<ActivityGroutListBinding, GroupListViewModel> {

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_grout_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public GroupListViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(GroupListViewModel.class);
    }

    @Override
    public void initViewObservable() {

        //注册文件下载的监听
        viewModel.sweepEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1:
                        startQrCode();
                        break;
                    case 2:
                        RxToast.normal("完成补货");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//使状态栏字体变为黑色
        // 可以调用该方法，设置不允许滑动退出
        setSwipeBackEnable(true);

        //获取测试数据
        viewModel.getList();



    }

    // 开始扫码
    public void startQrCode(){
        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted) {
                            Intent intent = new Intent(getBaseContext(), CaptureActivity.class);
                            startActivityForResult(intent, Constant.REQ_QR_CODE);
                        } else {
                            RxToast.normal("权限被拒绝，无法使用此功能");
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            KLog.d("扫描出的信息："+scanResult);
            RxToast.normal("扫码补货");

        }

    }



}
