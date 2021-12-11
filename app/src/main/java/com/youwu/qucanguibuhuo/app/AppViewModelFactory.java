package com.youwu.qucanguibuhuo.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.youwu.qucanguibuhuo.data.DemoRepository;
import com.youwu.qucanguibuhuo.ui.commodity.order_details.OrderDetailsViewModel;
import com.youwu.qucanguibuhuo.ui.commodity.order_reserve.OrderReserveViewModel;
import com.youwu.qucanguibuhuo.ui.login.LoginViewModel;
import com.youwu.qucanguibuhuo.ui.main.MainViewModel;
import com.youwu.qucanguibuhuo.ui.main.fragment.OneViewModel;
import com.youwu.qucanguibuhuo.ui.main.fragment.TwoViewModel;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHDetailsViewModel;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHOrderDetailsViewModel;
import com.youwu.qucanguibuhuo.ui.main.group_buy.CHRecordViewModel;
import com.youwu.qucanguibuhuo.ui.main.group_buy.TGOrderViewModel;
import com.youwu.qucanguibuhuo.ui.main.guizi.CabinetListViewModel;
import com.youwu.qucanguibuhuo.ui.main.historical_data.BHDetailsViewModel;
import com.youwu.qucanguibuhuo.ui.main.historical_data.BHRecordViewModel;
import com.youwu.qucanguibuhuo.ui.main.historical_data.QHRecordViewModel;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCLayoutViewModel;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCOrderViewModel;
import com.youwu.qucanguibuhuo.ui.main.myadmin.ZCSummaryViewModel;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.BookingRecordViewModel;
import com.youwu.qucanguibuhuo.ui.main.order_fragment.InventoryViewModel;
import com.youwu.qucanguibuhuo.ui.main.scan_open.GroupListViewModel;
import com.youwu.qucanguibuhuo.ui.main.scan_open.ScanOpenViewModel;
import com.youwu.qucanguibuhuo.ui.main.scan_open.fragment.GoodsViewModel;
import com.youwu.qucanguibuhuo.ui.set_up.ChangePasswordViewModel;
import com.youwu.qucanguibuhuo.ui.set_up.SetUpViewModel;
import com.youwu.qucanguibuhuo.ui.commodity.CommodityListViewModel;
import com.youwu.qucanguibuhuo.ui.commodity.fragment.AfternoonViewModel;
import com.youwu.qucanguibuhuo.ui.commodity.fragment.MorningViewModel;
import com.youwu.qucanguibuhuo.ui.sousuo.SouSuoViewModel;


import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final DemoRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideDemoRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, DemoRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       if (modelClass.isAssignableFrom(LoginViewModel.class)) {//登录页
            return (T) new LoginViewModel(mApplication, mRepository);
        }else if (modelClass.isAssignableFrom(MainViewModel.class)) {//首页
           return (T) new MainViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(OneViewModel.class)) {//首页第一个
           return (T) new OneViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(TwoViewModel.class)) {//门店列表
           return (T) new TwoViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CommodityListViewModel.class)) {//商品列表
           return (T) new CommodityListViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(MorningViewModel.class)) {//早餐商品列表
           return (T) new MorningViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(AfternoonViewModel.class)) {//午餐商品列表
           return (T) new AfternoonViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(SetUpViewModel.class)) {//设置页面
           return (T) new SetUpViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ChangePasswordViewModel.class)) {//修改密码页面
           return (T) new ChangePasswordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ZCSummaryViewModel.class)) {//早餐预约汇总
           return (T) new ZCSummaryViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ZCLayoutViewModel.class)) {//早餐预约分布
           return (T) new ZCLayoutViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ZCOrderViewModel.class)) {//早餐预约订单
           return (T) new ZCOrderViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(OrderReserveViewModel.class)) {//早餐柜预约订单
           return (T) new OrderReserveViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(OrderDetailsViewModel.class)) {//订单详情
           return (T) new OrderDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(InventoryViewModel.class)) {//待存货
           return (T) new InventoryViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(QHRecordViewModel.class)) {//取货记录
           return (T) new QHRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(BHRecordViewModel.class)) {//补货/清货记录
           return (T) new BHRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(BHDetailsViewModel.class)) {//补货/清货详情
           return (T) new BHDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(BookingRecordViewModel.class)) {//预购/团购记录
           return (T) new BookingRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(TGOrderViewModel.class)) {//团购订单
           return (T) new TGOrderViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CHRecordViewModel.class)) {//存货记录
           return (T) new CHRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CHDetailsViewModel.class)) {//存货详情
           return (T) new CHDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CHOrderDetailsViewModel.class)) {//存货订单详情
           return (T) new CHOrderDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(SouSuoViewModel.class)) {//搜索页面
           return (T) new SouSuoViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ScanOpenViewModel.class)) {//开门补货/开门清货
           return (T) new ScanOpenViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(GoodsViewModel.class)) {//开门补货/开门清货里的早餐和午餐列表
           return (T) new GoodsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(GroupListViewModel.class)) {//团购存货
           return (T) new GroupListViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CabinetListViewModel.class)) {//取餐柜列表
           return (T) new CabinetListViewModel(mApplication, mRepository);
       }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
