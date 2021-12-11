package com.youwu.qucanguibuhuo.data.source;

import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.entity.DemoEntity;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseBean;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);

    //登录
    Observable<BaseBean<Object>> CALOGIN(String username,String password);
    //检查更新
    Observable<BaseBean<UpDateBean>> GET_APP_VERSION();
    //获取用户信息
    Observable<BaseBean<UserBean>> GET_USER(String token);
    //修改密码
    Observable<BaseBean<Object>> UPDATE_PASSWORD(String token,String old_pwd,String new_pwd);
    //获取门店列表
    Observable<BaseBean<Object>> GET_STORE_LIST(String token, int page, int size);
    //获取智能柜列表
    Observable<BaseBean<Object>> GET_CABINET_LIST(String token,String store_id,int page, int size);


}
