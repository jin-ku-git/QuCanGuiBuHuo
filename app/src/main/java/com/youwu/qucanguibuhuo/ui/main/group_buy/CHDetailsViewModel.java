package com.youwu.qucanguibuhuo.ui.main.group_buy;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.main.historical_data.BHDetailsItemViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/27
 * 存货详情Model
 * 金库
 */

public class CHDetailsViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public CHDetailsViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<CHDetailsItemViewModel> PreOrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CHDetailsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_c_h_details);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public void getList(){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();

            bean.setOrder_cabinet("先每集团一号柜"+i);
            bean.setOrder_number("116416161616161"+i);//订单号
            bean.setOrder_phone("123456789"+i);//订单联系方式
            bean.setOrder_ch_order_time("2021-11-20 13:22:0"+i);//存货时间
            bean.setOrder_price("9."+i);//订单价格
            CHDetailsItemViewModel model=new CHDetailsItemViewModel(CHDetailsViewModel.this,bean);
            PreOrderList.add(model);

        }

    }

    /**
     * 获取条目下标
     *
     * @param chDetailsItemViewModel
     * @return
     */
    public int getItemPosition(CHDetailsItemViewModel chDetailsItemViewModel) {
        return PreOrderList.indexOf(chDetailsItemViewModel);
    }

}
