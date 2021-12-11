package com.youwu.qucanguibuhuo.ui.commodity.order_reserve;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.bean.PreOrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCLayoutItemViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/26
 * 预约订单Model
 * 金库
 */

public class OrderReserveViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<String> order_numEvent = new SingleLiveEvent<>();

    public ObservableField<String> order_num = new ObservableField<>();

    public OrderReserveViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<OrderReserveItemViewModel> OrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<OrderReserveItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_order_reserve);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public void getList(){
        int allNums=0;
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();
            bean.setOrder_number("XADS131655416164161631"+i);
            bean.setOrder_name("测试套餐"+i);
            bean.setOrder_num(""+i);
            bean.setOrder_cabinet("测试测试测试测试测试柜子"+i);
            bean.setOrder_cabinet("测试测试测试测试测试柜子"+i);
            bean.setOrder_phone("1234567890"+i);
            bean.setOrder_start_time("2021-11-26 08:03:0"+i);
            bean.setOrder_price("9."+i);
            OrderReserveItemViewModel model=new OrderReserveItemViewModel(OrderReserveViewModel.this,bean);
            OrderList.add(model);
            allNums += Integer.parseInt(bean.getOrder_num());
        }

        order_numEvent.setValue(allNums+"");
        order_num.set(allNums+"");
    }

    /**
     * 获取条目下标
     *
     * @param orderReserveItemViewModel
     * @return
     */
    public int getItemPosition(OrderReserveItemViewModel orderReserveItemViewModel) {
        return OrderList.indexOf(orderReserveItemViewModel);
    }

}
