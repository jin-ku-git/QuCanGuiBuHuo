package com.youwu.qucanguibuhuo.ui.main.myadmin;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.PreOrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/25
 * 早餐预约汇总Model
 * 金库
 */

public class ZCLayoutViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Integer> TypeEvent = new SingleLiveEvent<>();

    public ZCLayoutViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<ZCLayoutItemViewModel> PreOrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<ZCLayoutItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_order_layout);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //门店选择点击事件
    public BindingCommand MDOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            TypeEvent.setValue(1);
        }
    });


    public void getList(){
        for (int i=0;i<10;i++){
            PreOrderBean.PreOrder bean=new PreOrderBean.PreOrder();
            bean.setName("芝麻海苔肉松饭团套餐（三）"+i);
            bean.setStore_name("先每集团"+i);
            bean.setCabinet_name("先每集团门卫1号柜"+i);
            bean.setCabinet_lattice("A门"+i);
            bean.setNumbers(i+"");
            ZCLayoutItemViewModel model=new ZCLayoutItemViewModel(ZCLayoutViewModel.this,bean);
            PreOrderList.add(model);

        }
        PreOrderBean.PreOrder bean=new PreOrderBean.PreOrder();
        bean.setStore_name("门店名称");
        bean.setName("套餐名称");
        bean.setCabinet_name("早餐柜");
        bean.setCabinet_lattice("格子编号");
        bean.setNumbers("预约数量");
        ZCLayoutItemViewModel model=new ZCLayoutItemViewModel(ZCLayoutViewModel.this,bean);
        PreOrderList.add(0,model);
    }

    /**
     * 获取条目下标
     *
     * @param zcSummaryItemViewModel
     * @return
     */
    public int getItemPosition(ZCLayoutItemViewModel zcSummaryItemViewModel) {
        return PreOrderList.indexOf(zcSummaryItemViewModel);
    }

}
