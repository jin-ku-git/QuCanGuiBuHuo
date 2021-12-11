package com.youwu.qucanguibuhuo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

/**
 * 2021/11/24
 * 用户表
 * 金库
 */

public class UserBean extends BaseObservable implements Serializable {

    /**
     * "id": 2,
     * "name": "谢小龙",
     * "tel": "17854295316",
     * "user_name": "17854295316",
     * "store_id": "0",
     * "status": 1,
     * "add_time": 1638294068,
     * "up_time": 1639115328,
     * "company_id": 1,
     * "pwd": "52333ba0dab39aaab889a5c85968007e",
     * "encrypt": "N8FU40",
     * "operator_id": 1
     */


    private String id;//用户id
    private String name;//真实姓名
    private String user_name;//用户昵称
    private String tel;//用户手机号
    private String store_id;//关联门店ID
    private String status;//状态
    private String add_time;//
    private String up_time;//
    private String company_id;//用户ID
    private String pwd;//
    private String encrypt;//
    private String operator_id;//用户ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }
}
