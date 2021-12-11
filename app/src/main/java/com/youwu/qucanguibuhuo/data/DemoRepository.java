package com.youwu.qucanguibuhuo.data;

import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.data.source.HttpDataSource;
import com.youwu.qucanguibuhuo.data.source.LocalDataSource;
import com.youwu.qucanguibuhuo.entity.DemoEntity;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.http.BaseBean;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * Created by goldze on 2019/3/26.
 */
public class DemoRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private volatile static DemoRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    private DemoRepository(@NonNull HttpDataSource httpDataSource,
                           @NonNull LocalDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static DemoRepository getInstance(HttpDataSource httpDataSource,
                                             LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (DemoRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DemoRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Observable<Object> login() {
        return mHttpDataSource.login();
    }

    @Override
    public Observable<DemoEntity> loadMore() {
        return mHttpDataSource.loadMore();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return mHttpDataSource.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return mHttpDataSource.demoPost(catalog);
    }

    @Override
    public void saveUserName(String userName) {
        mLocalDataSource.saveUserName(userName);
    }

    @Override
    public void savePassword(String password) {
        mLocalDataSource.savePassword(password);
    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }

    /**
     * 是否换肤
     * @param state
     */
    @Override
    public void saveSwitch(String state) {
        mLocalDataSource.saveSwitch(state);
    }

    @Override
    public String getSwitch() {
        return mLocalDataSource.getSwitch();
    }


    /**
     *登录
     * @return
     * @param userName 手机号
     * @param password 密码
     */
    public Observable<BaseBean<Object>> CALOGIN(String userName, String password) {
        return mHttpDataSource.CALOGIN(userName,password);
    }
    /**
     * 检查更新
     * @return
     */
    public Observable<BaseBean<UpDateBean>> GET_APP_VERSION() {
        return mHttpDataSource.GET_APP_VERSION();
    }
    /**
     * 获取用户信息
     * @return
     */
    public Observable<BaseBean<UserBean>> GET_USER(String token) {
        return mHttpDataSource.GET_USER(token);
    }
    /**
     * 修改密码
     * @return
     */
    public Observable<BaseBean<Object>> UPDATE_PASSWORD(String token,String old_pwd,String new_pwd) {
        return mHttpDataSource.UPDATE_PASSWORD(token,old_pwd,new_pwd);
    }

    /**
     * 获取门店列表
     * @return
     */
    public Observable<BaseBean<Object>> GET_STORE_LIST (String token, int page, int size) {
        return mHttpDataSource.GET_STORE_LIST(token,page,size);
    }
    /**
     * 获取智能柜列表
     * @return
     */
    public Observable<BaseBean<Object>> GET_CABINET_LIST (String token,String store_id, int page, int size) {
        return mHttpDataSource.GET_CABINET_LIST(token,store_id,page,size);
    }
}
