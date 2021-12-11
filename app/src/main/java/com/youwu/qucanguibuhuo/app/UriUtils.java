package com.youwu.qucanguibuhuo.app;


import android.util.Log;

import com.youwu.qucanguibuhuo.bean.PostBean;


public class UriUtils {


    public static String getRequestPostUrl(Object dataString) {
        Log.d("requstdata", "dataString  " + dataString);
        PostBean postBean = new PostBean();
        postBean.setData(dataString);
        postBean.setToken(AppApplication.spUtils.getString("token", ""));
        String requstData = AppApplication.gson.toJson(postBean);


        Log.d("requstdata", "jsonData  " + AppApplication.gson.toJson(postBean));
        Log.d("requstdata", "URL  " + AppApplication.gson.toJson(postBean));
        Log.d("requstdata", "encodedata  " + requstData);

        return requstData;
    }


}
