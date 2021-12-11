package com.youwu.qucanguibuhuo.ui.main.scan_open.fragment;

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
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryItemViewModel;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryTGItemViewModel;

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

public class GoodsViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<UserBean> user = new ObservableField<>();

    public ObservableField<Integer> type = new ObservableField<>();
    //开门事件
    public SingleLiveEvent<OrderBean> open_type = new SingleLiveEvent<>();


    public GoodsViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    /**
     * 搜索订单
     */
    //给RecyclerView添加ObservableList
    public ObservableList<GoodsItemViewModel> OrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<GoodsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_goods);


    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("下拉刷新");

        }
    });



    public void getList(String page,int type){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();
            bean.setOrder_number(i+"号");
            bean.setOrder_name("测试套餐"+i+type+"-"+page);
            bean.setOrder_bh_stock("1"+i);
            bean.setOrder_bh_yuyue("2"+i);

            bean.setOrder_type(type);

            GoodsItemViewModel model=new GoodsItemViewModel(GoodsViewModel.this,bean);
                OrderList.add(model);



        }
    }


    /**
     * 获取条目下标
     *
     * @param goodsItemViewModel
     * @return
     */
    public int getItemPosition(GoodsItemViewModel goodsItemViewModel) {
        return OrderList.indexOf(goodsItemViewModel);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
