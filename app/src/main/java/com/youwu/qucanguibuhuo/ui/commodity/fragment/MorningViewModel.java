package com.youwu.qucanguibuhuo.ui.commodity.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.PackageBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * 2021/11/24
 * 金库
 */

public class MorningViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<UserBean> user = new ObservableField<>();
    //下拉刷新完成
    public SingleLiveEvent finishRefreshing = new SingleLiveEvent<>();

    //给RecyclerView添加ObservableList
    public ObservableList<MorningItemViewModel> PackageList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<MorningItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_package);

    public MorningViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("下拉刷新");

        }
    });

    public void getList(int type){
        for (int i=0;i<10;i++){
            PackageBean.PackageOrderBean bean=new PackageBean.PackageOrderBean();
            if (type==1){
                bean.setName("早餐套餐"+i);
            }else {
                bean.setName("午餐套餐"+i);
            }

            bean.setPackage_stock(i+"");
            bean.setPackage_reserve(i+"");
            bean.setPackage_door("A"+i);
            MorningItemViewModel model=new MorningItemViewModel(MorningViewModel.this,bean);
            PackageList.add(model);
        }
    }


    /**
     * 获取条目下标
     *
     * @param morningItemViewModel
     * @return
     */
    public int getItemPosition(MorningItemViewModel morningItemViewModel) {
        return PackageList.indexOf(morningItemViewModel);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
