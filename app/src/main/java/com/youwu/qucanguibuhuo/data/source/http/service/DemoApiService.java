package com.youwu.qucanguibuhuo.data.source.http.service;

import com.youwu.qucanguibuhuo.bean.CabinetBean;
import com.youwu.qucanguibuhuo.bean.UpDateBean;
import com.youwu.qucanguibuhuo.bean.UserBean;
import com.youwu.qucanguibuhuo.entity.DemoEntity;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseBean;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by goldze on 2017/6/15.
 */

public interface DemoApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<DemoEntity>> demoGet();

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    Observable<BaseResponse<DemoEntity>> demoPost(@Field("catalog") String catalog);


    /**
     * 登录
     * @param username 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("cpfr/login/")
    Observable<BaseBean<Object>> CALOGIN(@Field("user_name") String username,@Field("pwd") String password);

    /**
     * 检查更新
     * @return
     */
    @POST("cpfr/getAppVersion")
    Observable<BaseBean<UpDateBean>> GET_APP_VERSION();

    /**
     * 获取用户信息
     * @return
     */
    @FormUrlEncoded
    @POST("cpfr/member/getUserInfo")
    Observable<BaseBean<UserBean>> GET_USER(@Field("token") String token);

    /**
     * 修改密码
     * @return
     */
    @FormUrlEncoded
    @POST("cpfr/member/updatePwd")
    Observable<BaseBean<Object>> UPDATE_PASSWORD(@Field("token") String token,@Field("old_pwd") String old_pwd,@Field("new_pwd") String new_pwd);
    /**
     * 获取门店列表
     * @return
     */
    @FormUrlEncoded
    @POST("cpfr/member/getStoreList")
    Observable<BaseBean<Object>> GET_STORE_LIST(@Field("token") String token, @Field("page") int page, @Field("size") int size);
    /**
     * 获取智能柜列表
     * @return
     */
    @FormUrlEncoded
    @POST("cpfr/member/getCabinetList")
    Observable<BaseBean<Object>> GET_CABINET_LIST(@Field("token") String token, @Field("store_id") String store_id,@Field("page") int page, @Field("size") int size);
}
