package com.youwu.qucanguibuhuo.ui.main.group_buy;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/26
 * 存货订单详情Model
 * 金库
 */

public class CHOrderDetailsViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<OrderBean> order = new ObservableField<>();
    //使用LiveData
    public SingleLiveEvent<Integer> loadUrlEvent = new SingleLiveEvent<>();

    public CHOrderDetailsViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<CHOrderDetailsItemViewModel> CHOrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CHOrderDetailsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_c_h_order);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    //一键退款点击事件
    public BindingCommand refundOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxToast.error("申请退款");
            loadUrlEvent.setValue(1);
        }
    });
    //打电话点击事件
    public BindingCommand phoneOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxToast.error("打电话");
            showPhone("123456");
        }
    });



    public void getList(){
        for (int i=0;i<2;i++){
            OrderBean bean=new OrderBean();

            bean.setOrder_cabinet("先每集团一号柜"+i);
            bean.setOrder_number("116416161616161"+i);//订单号
            bean.setOrder_phone("123456789"+i);//订单联系方式
            bean.setOrder_ch_order_time("2"+i);//存货时间
            bean.setOrder_price("9."+i);//订单价格
            CHOrderDetailsItemViewModel model=new CHOrderDetailsItemViewModel(CHOrderDetailsViewModel.this,bean);
            CHOrderList.add(model);

        }

    }

    /**
     * 获取条目下标
     *
     * @param chOrderDetailsItemViewModel
     * @return
     */
    public int getItemPosition(CHOrderDetailsItemViewModel chOrderDetailsItemViewModel) {
        return CHOrderList.indexOf(chOrderDetailsItemViewModel);
    }
}
