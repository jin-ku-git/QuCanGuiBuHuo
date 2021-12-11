package com.youwu.qucanguibuhuo.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  套餐列表
 */
public class PackageBean implements Serializable {

    private List<PackageOrderBean> Package_order;

    public List<PackageOrderBean> getPackage_order() {
        return Package_order;
    }

    public void setPackage_order(List<PackageOrderBean> package_order) {
        Package_order = package_order;
    }

    public static class PackageOrderBean implements Serializable{
        private String id;//id
        private String name;//套餐名称
        private String image;//套餐图片
        private String package_door;//套餐所在门
        private String package_stock;//库存
        private String package_reserve;//预定


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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPackage_door() {
            return package_door;
        }

        public void setPackage_door(String package_door) {
            this.package_door = package_door;
        }

        public String getPackage_stock() {
            return package_stock;
        }

        public void setPackage_stock(String package_stock) {
            this.package_stock = package_stock;
        }

        public String getPackage_reserve() {
            return package_reserve;
        }

        public void setPackage_reserve(String package_reserve) {
            this.package_reserve = package_reserve;
        }
    }
}
