package com.youwu.qucanguibuhuo.ui.main.order_fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * 2021/11/27
 * 金库
 */

public class BookingRecordViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<UserBean> user = new ObservableField<>();
    //下拉刷新完成
    public SingleLiveEvent finishRefreshing = new SingleLiveEvent<>();

    //给RecyclerView添加ObservableList
    public ObservableList<BookingRecordItemViewModel> OrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<BookingRecordItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_order_booking);

    public BookingRecordViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("下拉刷新");

        }
    });

    public void getList(String type){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();
            bean.setOrder_number("XADS131655416164161631"+i+type);

            bean.setOrder_cabinet("测试测试测试测试测试柜子"+i+type);

            bean.setOrder_pickup_time("2021-11-26 08:03:0"+i);
            bean.setOrder_type(Integer.parseInt(type));
            BookingRecordItemViewModel model=new BookingRecordItemViewModel(BookingRecordViewModel.this,bean);
            OrderList.add(model);

        }
    }


    /**
     * 获取条目下标
     *
     * @param bookingRecordItemViewModel
     * @return
     */
    public int getItemPosition(BookingRecordItemViewModel bookingRecordItemViewModel) {
        return OrderList.indexOf(bookingRecordItemViewModel);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
