package com.youwu.qucanguibuhuo.ui.main.guizi;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.library.baseAdapters.BR;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.youwu.qucanguibuhuo.R;
import com.youwu.qucanguibuhuo.app.AppApplication;
import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.main.fragment.TwoItemViewModel;
import com.youwu.qucanguibuhuo.ui.main.fragment.TwoViewModel;
import com.youwu.qucanguibuhuo.utils_view.RxToast;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseBean;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import static com.youwu.qucanguibuhuo.app.AppApplication.toPrettyFormat;


/**
 * 2021/12/10
 * 取餐柜列表
 */

public class CabinetListViewModel extends BaseViewModel<DemoRepository> {


    //下拉刷新完成
    public SingleLiveEvent finishRefreshing = new SingleLiveEvent<>();

    public CabinetListViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    //
    public SingleLiveEvent<Integer> IntegerEvent = new SingleLiveEvent<>();

    //导航
    public SingleLiveEvent<CabinetBean.OrderDataBean> DHEvent = new SingleLiveEvent<>();

    //给RecyclerView添加ObservableList
    public ObservableList<CabinetListItemViewModel> foodList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<CabinetListItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_food_address);

    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("下拉刷新");

        }
    });

    public void getList(String store_id,int page,int size){

        model.GET_CABINET_LIST(AppApplication.spUtils.getString("token"),store_id,page,size)
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new DisposableObserver<BaseBean<Object>>() {
                    @Override
                    public void onNext(BaseBean<Object> response) {
                        String submitJson = new Gson().toJson(response);
                        Log.e("取餐柜列表-----已解析",toPrettyFormat(submitJson));
                        if (0==response.getCode()){
                            String submitJsonData = new Gson().toJson(response);
                            CabinetBean goodsTypesBean= JSON.parseObject(toPrettyFormat(submitJsonData), CabinetBean.class);
                            for (CabinetBean.OrderDataBean entity : goodsTypesBean.data) {
                                CabinetListItemViewModel itemViewModel = new CabinetListItemViewModel(CabinetListViewModel.this, entity);
                                //双向绑定动态添加Item
                                foodList.add(itemViewModel);
                            }
                        }else {
                            RxToast.normal(response.getMessage());
                        }
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        //关闭对话框
                        dismissDialog();
                        if (throwable instanceof ResponseThrowable) {
                            ToastUtils.showShort(((ResponseThrowable) throwable).message);
                        }
                    }
                    @Override
                    public void onComplete() {
                        //关闭对话框
                        dismissDialog();
                    }
                });

    }

    /**
     * 获取条目下标
     *
     * @param cabinetListItemViewModel
     * @return
     */
    public int getItemPosition(CabinetListItemViewModel cabinetListItemViewModel) {
        return foodList.indexOf(cabinetListItemViewModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
