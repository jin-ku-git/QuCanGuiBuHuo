package me.goldze.mvvmhabit.http;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {

    public int code;
    public String message;
    public String type;
    public T data;
    public T meta;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return code == 200;
    }

    public T getMeta() {
        return meta;
    }

    public void setMeta(T meta) {
        this.meta = meta;
    }
}
