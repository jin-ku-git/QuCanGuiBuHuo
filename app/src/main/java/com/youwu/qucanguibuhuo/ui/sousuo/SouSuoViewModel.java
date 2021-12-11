package com.youwu.qucanguibuhuo.ui.sousuo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHDetailsItemViewModel;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHDetailsViewModel;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryItemViewModel;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/29
 * 搜索Model
 */

public class SouSuoViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public ObservableField<Integer> type = new ObservableField<>();

    public SingleLiveEvent<Integer> typeEvent = new SingleLiveEvent<>();


    public SouSuoViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    /**
     * 预约订单
     */
    //给RecyclerView添加ObservableList
    public ObservableList<SouSuoItemViewModel> YuYueList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<SouSuoItemViewModel> YYitemBinding = ItemBinding.of(BR.viewModel, R.layout.item_sou_suo);

    /**
     * 团购订单
     */
    //给RecyclerView添加ObservableList
    public ObservableList<SouSuoTGItemViewModel> TuanGouList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<SouSuoTGItemViewModel> TGitemBinding = ItemBinding.of(BR.viewModel, R.layout.item_sou_suo_tg);

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

    public void getList(int type){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();
            bean.setOrder_number("XADS131655416164161631"+i+type);
            bean.setOrder_name("测试套餐"+i+type);
            bean.setOrder_cabinet("测试测试测试测试测试柜子"+i+type);
            bean.setOrder_phone("1234567890"+i);
            bean.setOrder_start_time("2021-11-26 08:03:0"+i);
            bean.setOrder_ch_order_time("2021-11-26 08:03:0"+i);
            bean.setOrder_price("9."+i+type);

            if (type==1){
                SouSuoItemViewModel model=new SouSuoItemViewModel(SouSuoViewModel.this,bean);
                YuYueList.add(model);
            }else {
                SouSuoTGItemViewModel model=new SouSuoTGItemViewModel(SouSuoViewModel.this,bean);
                TuanGouList.add(model);
            }

        }
    }


    /**
     * 获取条目下标
     *
     * @param souSuoItemViewModel
     * @return
     */
    public int getItemPosition(SouSuoItemViewModel souSuoItemViewModel) {
        return YuYueList.indexOf(souSuoItemViewModel);
    }

    /**
     * 获取条目下标
     *
     * @param souSuoTGItemViewModel
     * @return
     */
    public int getItemPosition(SouSuoTGItemViewModel souSuoTGItemViewModel) {
        return TuanGouList.indexOf(souSuoTGItemViewModel);
    }
}
