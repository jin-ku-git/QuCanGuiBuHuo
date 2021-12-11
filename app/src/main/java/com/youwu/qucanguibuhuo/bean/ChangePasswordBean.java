package com.youwu.qucanguibuhuo.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

/**
 * 2021/11/24
 * 修改密码实体类
 * 金库
 */

public class ChangePasswordBean extends BaseObservable implements Serializable {
    private String used_password;//原密码
    private String new_password;//新密码
    private String determine_new_password;//确认新密码

    public String getUsed_password() {
        return used_password;
    }

    public void setUsed_password(String used_password) {
        this.used_password = used_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getDetermine_new_password() {
        return determine_new_password;
    }

    public void setDetermine_new_password(String determine_new_password) {
        this.determine_new_password = determine_new_password;
    }
}
