package com.youwu.qucanguibuhuo.data.source.http;

import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.source.HttpDataSource;
import com.youwu.qucanguibuhuo.data.source.http.service.DemoApiService;
import com.youwu.qucanguibuhuo.entity.DemoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import me.goldze.mvvmhabit.http.BaseBean;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private DemoApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(DemoApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(DemoApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Object> login() {
        return Observable.just(new Object()).delay(3, TimeUnit.SECONDS); //延迟3秒
    }

    @Override
    public Observable<DemoEntity> loadMore() {
        return Observable.create(new ObservableOnSubscribe<DemoEntity>() {
            @Override
            public void subscribe(ObservableEmitter<DemoEntity> observableEmitter) throws Exception {
                DemoEntity entity = new DemoEntity();
                List<DemoEntity.ItemsEntity> itemsEntities = new ArrayList<>();
                //模拟一部分假数据
                for (int i = 0; i < 10; i++) {
                    DemoEntity.ItemsEntity item = new DemoEntity.ItemsEntity();
                    item.setId(-1);
                    item.setName("模拟条目");
                    itemsEntities.add(item);
                }
                entity.setItems(itemsEntities);
                observableEmitter.onNext(entity);
            }
        }).delay(3, TimeUnit.SECONDS); //延迟3秒
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return apiService.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return apiService.demoPost(catalog);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Observable<BaseBean<Object>> CALOGIN(String username,String password) {
        return apiService.CALOGIN(username,password);
    }

    /**
     * 检查更新
     * @return
     */
    @Override
    public Observable<BaseBean<UpDateBean>> GET_APP_VERSION() {
        return apiService.GET_APP_VERSION();
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public Observable<BaseBean<UserBean>> GET_USER(String token) {
        return apiService.GET_USER(token);
    }

    /**
     * 修改密码
     * @return
     */
    @Override
    public Observable<BaseBean<Object>> UPDATE_PASSWORD(String token,String old_pwd,String new_pwd) {
        return apiService.UPDATE_PASSWORD(token,old_pwd,new_pwd);
    }
    /**
     * 获取门店列表
     * @return
     */
    @Override
    public Observable<BaseBean<Object>> GET_STORE_LIST(String token, int page, int size) {
        return apiService.GET_STORE_LIST(token,page,size);
    }
    /**
     * 获取智能柜列表
     * @return
     */
    @Override
    public Observable<BaseBean<Object>> GET_CABINET_LIST(String token, String store_id,int page, int size) {
        return apiService.GET_CABINET_LIST(token,store_id,page,size);
    }
}
