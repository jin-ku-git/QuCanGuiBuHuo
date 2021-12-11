package com.youwu.qucanguibuhuo.ui.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ScreenUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.UserBean;

import com.youwu.qucanguibuhuo.databinding.FragmentOneBinding;
import com.youwu.qucanguibuhuo.dialog.DiyDialog;
import com.youwu.qucanguibuhuo.dialog.GroupPop;
import com.youwu.qucanguibuhuo.dialog.bean.TableItemBean;
import com.youwu.qucanguibuhuo.scanning.activity.CaptureActivity;
import com.youwu.qucanguibuhuo.ui.main.scan_open.GroupListActivity;
import com.youwu.qucanguibuhuo.ui.main.scan_open.ScanOpenActivity;
import com.youwu.qucanguibuhuo.utils.Constant;
import com.youwu.qucanguibuhuo.utils_view.RxToast;
import com.youwu.qucanguibuhuo.utils_view.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.KLog;
import static android.app.Activity.RESULT_OK;

/**
 * 2021/11/22
 * 首页第一个页面
 * 金库
 */

public class OneFragment extends BaseFragment<FragmentOneBinding, OneViewModel> {

    int state_type=0;//1扫码补货 2团购存货

    DiyDialog diyDialog ;
    @Override
    public void initParam() {
        super.initParam();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_one;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * 绑定Model
     * @return
     */
    @Override
    public OneViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(OneViewModel.class);
    }

    @Override
    public void initData() {
        StatusBarUtil.setRootViewFitsSystemWindows(getActivity(), true);
        //修改状态栏是状态栏透明
        StatusBarUtil.setTransparentForWindow(getActivity());
        StatusBarUtil.setDarkMode(getActivity());//使状态栏字体变为黑色

        viewModel.getUser();

        diyDialog =new DiyDialog(getContext());
    }
    // 开始扫码
    public void startQrCode(){
        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted) {
                            Intent intent = new Intent(getActivity(), CaptureActivity.class);
                            startActivityForResult(intent, Constant.REQ_QR_CODE);
                        } else {
                            RxToast.normal("权限被拒绝，无法使用此功能");
                        }
                    }
                });
    }

    @Override
    public void initViewObservable() {
        viewModel.passEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case 1:
                        state_type=1;
                        KLog.d("走了");
                        startQrCode();
                        break;
                    case 2:
                        state_type=2;
                        startQrCode();
                           break;
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
            KLog.d("state_type："+state_type);
            if (state_type==1){

            new XPopup.Builder(getContext())
                    .maxHeight((int) (ScreenUtils.getAppScreenHeight() * 0.7))
                    .asCenterList("请选择", new String[]{"补货", "清货"}, new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {

                                Bundle bundle=new Bundle();
                                bundle.putInt("state_type", position);
                                startActivity(ScanOpenActivity.class,bundle);
                        }
                    }).show();

            }else {
                initTG();

            }

        }

    }

    private void initTG() {
        List<TableItemBean> data=new ArrayList<>();

        for (int i=0;i<8;i++){
            TableItemBean tableItemBean=new TableItemBean();
            tableItemBean.setCabinet_number("A"+i);
            data.add(tableItemBean);
        }
        GroupPop groupPop =new GroupPop(getContext(), data);
        groupPop.setClickInterface(new GroupPop.ClickInterface() {
            @Override
            public void click(int position, TableItemBean bean) {
//                viewModel.openGuizi(bean);
                groupPop.dismiss();
                startActivity(GroupListActivity.class);
            }
        });

          new  XPopup.Builder(getContext())
                .asCustom(groupPop)
                .show();
    }


}
