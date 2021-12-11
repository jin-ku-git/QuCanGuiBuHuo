package com.youwu.qucanguibuhuo.ui.commodity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;


import com.youwu.qucanguibuhuo.BR;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppViewModelFactory;
import com.youwu.qucanguibuhuo.databinding.FragmentAfternoonBinding;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 2021/11/24
 * 午餐列表
 */

public class AfternoonFragment extends BaseFragment<FragmentAfternoonBinding,AfternoonViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_afternoon;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public AfternoonViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(AfternoonViewModel.class);
    }
}
