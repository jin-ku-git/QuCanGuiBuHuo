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
       if (modelClass.isAssignableFrom(LoginViewModel.class)) {//?????????
            return (T) new LoginViewModel(mApplication, mRepository);
        }else if (modelClass.isAssignableFrom(MainViewModel.class)) {//??????
           return (T) new MainViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(OneViewModel.class)) {//???????????????
           return (T) new OneViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(TwoViewModel.class)) {//????????????
           return (T) new TwoViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CommodityListViewModel.class)) {//????????????
           return (T) new CommodityListViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(MorningViewModel.class)) {//??????????????????
           return (T) new MorningViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(AfternoonViewModel.class)) {//??????????????????
           return (T) new AfternoonViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(SetUpViewModel.class)) {//????????????
           return (T) new SetUpViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ChangePasswordViewModel.class)) {//??????????????????
           return (T) new ChangePasswordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ZCSummaryViewModel.class)) {//??????????????????
           return (T) new ZCSummaryViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ZCLayoutViewModel.class)) {//??????????????????
           return (T) new ZCLayoutViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ZCOrderViewModel.class)) {//??????????????????
           return (T) new ZCOrderViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(OrderReserveViewModel.class)) {//?????????????????????
           return (T) new OrderReserveViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(OrderDetailsViewModel.class)) {//????????????
           return (T) new OrderDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(InventoryViewModel.class)) {//?????????
           return (T) new InventoryViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(QHRecordViewModel.class)) {//????????????
           return (T) new QHRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(BHRecordViewModel.class)) {//??????/????????????
           return (T) new BHRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(BHDetailsViewModel.class)) {//??????/????????????
           return (T) new BHDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(BookingRecordViewModel.class)) {//??????/????????????
           return (T) new BookingRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(TGOrderViewModel.class)) {//????????????
           return (T) new TGOrderViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CHRecordViewModel.class)) {//????????????
           return (T) new CHRecordViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CHDetailsViewModel.class)) {//????????????
           return (T) new CHDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CHOrderDetailsViewModel.class)) {//??????????????????
           return (T) new CHOrderDetailsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(SouSuoViewModel.class)) {//????????????
           return (T) new SouSuoViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(ScanOpenViewModel.class)) {//????????????/????????????
           return (T) new ScanOpenViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(GoodsViewModel.class)) {//????????????/???????????????????????????????????????
           return (T) new GoodsViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(GroupListViewModel.class)) {//????????????
           return (T) new GroupListViewModel(mApplication, mRepository);
       }else  if (modelClass.isAssignableFrom(CabinetListViewModel.class)) {//???????????????
           return (T) new CabinetListViewModel(mApplication, mRepository);
       }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
