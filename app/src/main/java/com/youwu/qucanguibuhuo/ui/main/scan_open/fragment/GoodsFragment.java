package com.youwu.qucanguibuhuo.ui.main.scan_open.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lxj.xpopup.XPopup;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.databinding.FragmentGoodsBinding;
import com.youwu.qucanguibuhuo.databinding.FragmentInventoryBinding;
import com.youwu.qucanguibuhuo.dialog.BuhuoDialog;
import com.youwu.qucanguibuhuo.dialog.ClearDialog;
import com.youwu.qucanguibuhuo.dialog.bean.CabinetItemBean;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryViewModel;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 2021/11/30
 * 补货/清货
 */

public class GoodsFragment extends BaseFragment<FragmentGoodsBinding, GoodsViewModel> {

    private String page;//第几页
    private int  type;//0 开门补货 1 开门清货

    private CabinetItemBean bean=new CabinetItemBean();

    public GoodsFragment(String page, int type) {
        super();
        this.page=page;
        this.type=type;
    }


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_goods;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public GoodsViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(GoodsViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //开门监听事件
        viewModel.open_type.observe(this, new Observer<OrderBean>() {
            @Override
            public void onChanged(OrderBean orderBean) {
                switch (orderBean.getOrder_type()){
                    case 0:
                        bean.setPackage_name(orderBean.getOrder_name());
                        bean.setEditNum(orderBean.getOrder_bh_stock());
                        bean.setEndNum(orderBean.getOrder_bh_yuyue());
                        bean.setStock("5");
                        showDialog(bean,0);
                        break;
                        case 1:
                            bean.setPackage_name(orderBean.getOrder_name());
                            bean.setClearNum(orderBean.getOrder_bh_yuyue());
                        showDialog(bean,1);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

        viewModel.type.set(type);
        //获取测试数据
        viewModel.getList(page,type);
    }

    private void showDialog(CabinetItemBean bean, int i){

        if (i==0){
            BuhuoDialog dialog =new BuhuoDialog(getContext(), bean);
            new  XPopup.Builder(getContext())
                    .autoOpenSoftInput(true)
                    .asCustom(dialog)
                    .show();
        }else {
            ClearDialog dialog_clear =new ClearDialog(getContext(), bean);
            new  XPopup.Builder(getContext())
                    .autoOpenSoftInput(true)
                    .asCustom(dialog_clear)
                    .show();
        }



    }

}
