package com.youwu.qucanguibuhuo.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  取餐柜详情列表
 */
public class CabinetBean implements Serializable {

    public List<OrderDataBean> data;


    public List<OrderDataBean> getData() {
        return data;
    }

    public void setData(List<OrderDataBean> data) {
        this.data = data;
    }

    public static class OrderDataBean implements Serializable{
        private String id;//id
        private String name;//取餐柜名称
        private String status;//状态 1待开业,2营业中，3停业中4，关停'
        private String address;//取餐柜地址
        private String image;//取餐柜图片

        private String longitude;//取餐柜经度
        private String latitude;//取餐柜纬度


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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
