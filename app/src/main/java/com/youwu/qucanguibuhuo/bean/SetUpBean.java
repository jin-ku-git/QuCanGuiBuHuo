package com.youwu.qucanguibuhuo.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

/**
 * 2021/11/24
 * 设置实体类
 * 金库
 */

public class SetUpBean extends BaseObservable implements Serializable {
    private String version_number;//版本号

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }
}
