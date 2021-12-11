package com.youwu.qucanguibuhuo.ui.main.group_buy;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.main.historical_data.BHRecordItemViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/27
 * 存货记录Model
 * 金库
 */

public class CHRecordViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Integer> typeEvent = new SingleLiveEvent<>();

    public CHRecordViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<CHRecordItemViewModel> PreOrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CHRecordItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_c_h_record);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //门店选择点击事件
    public BindingCommand store_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(1);
        }
    });
    //柜子选择点击事件
    public BindingCommand cabinet_onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            typeEvent.setValue(2);
        }
    });


    public void getList(){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();

            bean.setOrder_cabinet("清货柜子"+i);
            bean.setOrder_ch_order_time("2021-11-26 08:03:0"+i);
            bean.setOrder_ch_order_number("1"+i);

            CHRecordItemViewModel model=new CHRecordItemViewModel(CHRecordViewModel.this,bean);
            PreOrderList.add(model);

        }

    }

    /**
     * 获取条目下标
     *
     * @param chRecordItemViewModel
     * @return
     */
    public int getItemPosition(CHRecordItemViewModel chRecordItemViewModel) {
        return PreOrderList.indexOf(chRecordItemViewModel);
    }

}
