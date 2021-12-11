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
import com.youwu.qucanguibuhuo.ui.sousuo.SouSuoTGItemViewModel;

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

public class InventoryViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<UserBean> user = new ObservableField<>();

    public ObservableField<Integer> type = new ObservableField<>();
    //下拉刷新完成
    public SingleLiveEvent finishRefreshing = new SingleLiveEvent<>();

    /**
     * 搜索订单
     */
    //给RecyclerView添加ObservableList
    public ObservableList<InventoryItemViewModel> OrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<InventoryItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_order_stock);

    /**
     * 团购订单
     */
    //给RecyclerView添加ObservableList
    public ObservableList<InventoryTGItemViewModel> TuanGouList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<InventoryTGItemViewModel> TGitemBinding = ItemBinding.of(BR.viewModel, R.layout.item_inventory_tg);

    public InventoryViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

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
            bean.setOrder_number("XADS131655416164161631"+i+type+"-"+page);
            bean.setOrder_name("测试套餐"+i+type+"-"+page);
            bean.setOrder_cabinet("测试测试测试测试测试柜子"+i+type);
            bean.setOrder_phone("1234567890"+i);
            bean.setOrder_start_time("2021-11-26 08:03:0"+i);
            bean.setOrder_ch_order_time("2021-11-26 08:03:0"+i);
            bean.setOrder_price("9."+i+type);

            if (type==1){
                InventoryItemViewModel model=new InventoryItemViewModel(InventoryViewModel.this,bean);
                OrderList.add(model);
            }else {
                InventoryTGItemViewModel model=new InventoryTGItemViewModel(InventoryViewModel.this,bean);
                TuanGouList.add(model);
            }


        }
    }


    /**
     * 获取条目下标
     *
     * @param inventoryItemViewModel
     * @return
     */
    public int getItemPosition(InventoryItemViewModel inventoryItemViewModel) {
        return OrderList.indexOf(inventoryItemViewModel);
    }


    /**
     * 获取条目下标
     *
     * @param inventoryTGItemViewModel
     * @return
     */
    public int getItemPosition(InventoryTGItemViewModel inventoryTGItemViewModel) {
        return TuanGouList.indexOf(inventoryTGItemViewModel);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
