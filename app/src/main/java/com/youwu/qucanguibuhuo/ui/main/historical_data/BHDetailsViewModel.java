package com.youwu.qucanguibuhuo.ui.main.historical_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.bean.OrderBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 2021/11/27
 * 补货详情/清货详情Model
 * 金库
 */

public class BHDetailsViewModel extends BaseViewModel<DemoRepository> {

    //使用LiveData
    public SingleLiveEvent<String> loadUrlEvent = new SingleLiveEvent<>();

    public BHDetailsViewModel(@NonNull Application application, DemoRepository repository) {
        super(application,repository);
    }

    //给RecyclerView添加PreOrderList
    public ObservableList<BHDetailsItemViewModel> PreOrderList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<BHDetailsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_b_h_details);

    //返回点击事件
    public BindingCommand FinishOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public void getList(int type){
        for (int i=0;i<10;i++){
            OrderBean bean=new OrderBean();

            if (type==1){
                bean.setOrder_name("补货营养套餐"+i);
            }else {
                bean.setOrder_name("清货营养套餐"+i);
            }
            bean.setOrder_bh_number("1"+i);//补货数量
            bean.setOrder_bh_actual_number("1"+i);//实际补货数量
            bean.setOrder_qh_number("2"+i);//清货数量

            BHDetailsItemViewModel model=new BHDetailsItemViewModel(BHDetailsViewModel.this,bean);
            PreOrderList.add(model);

        }

    }

    /**
     * 获取条目下标
     *
     * @param bhDetailsItemViewModel
     * @return
     */
    public int getItemPosition(BHDetailsItemViewModel bhDetailsItemViewModel) {
        return PreOrderList.indexOf(bhDetailsItemViewModel);
    }

}
