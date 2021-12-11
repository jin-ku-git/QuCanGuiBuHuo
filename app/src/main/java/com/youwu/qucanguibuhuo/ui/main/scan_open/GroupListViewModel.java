package com.youwu.qucanguibuhuo.ui.main.scan_open;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHDetailsItemViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/12/01
 * 团购存货Model
 * 金库
 */

public class GroupListViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();
    //扫码
    public SingleLiveEvent<Integer> sweepEvent = new SingleLiveEvent<>();

    public GroupListViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<GroupListItemViewModel> TGOrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<GroupListItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_grout_list);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //扫码补货点击事件
    public BindingCommand sweepOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            sweepEvent.setValue(1);
        }
    });
    //完成补货点击事件
    public BindingCommand completeOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            sweepEvent.setValue(2);
        }
    });

    public void getList(){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();
            bean.setOrder_price("99."+i);
            GroupListItemViewModel model=new GroupListItemViewModel(GroupListViewModel.this,bean);
            TGOrderList.add(model);

        }

    }

    /**
     * 获取条目下标
     *
     * @param groupListItemViewModel
     * @return
     */
    public int getItemPosition(GroupListItemViewModel groupListItemViewModel) {
        return TGOrderList.indexOf(groupListItemViewModel);
    }

}
