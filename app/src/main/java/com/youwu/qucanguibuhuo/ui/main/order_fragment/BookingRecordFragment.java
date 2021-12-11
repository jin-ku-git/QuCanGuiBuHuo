package com.youwu.qucanguibuhuo.ui.main.order_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.FragmentBookingRecordBinding;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 2021/11/26
 * 预购和团购记录
 */

public class BookingRecordFragment extends BaseFragment<FragmentBookingRecordBinding, BookingRecordViewModel> {

    private String type;

    public BookingRecordFragment(String type) {
        super();
        this.type=type;
    }


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_booking_record;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public BookingRecordViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(BookingRecordViewModel.class);
    }

    @Override
    public void initData() {

        //获取测试数据
//        viewModel.getList(type);

        //下拉刷新
        binding.smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                    viewModel.OrderList.clear();
                //获取测试数据
                viewModel.getList(type);
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        binding.smartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                RxToast.normal("上拉加载");
                refreshLayout.finishLoadMore(true);
            }
        });
    }

}
