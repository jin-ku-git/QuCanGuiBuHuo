package com.youwu.qucanguibuhuo.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  预约餐柜子详情列表
 */
public class PreOrderBean implements Serializable {

    private List<PreOrder> item;


    public List<PreOrder> getItem() {
        return item;
    }

    public void setItem(List<PreOrder> item) {
        this.item = item;
    }

    public static class PreOrder implements Serializable{
        private String id;//id
        private String name;//早餐预约套餐名称
        private String numbers;//早餐预约数量
        private String store_name;//预约门店名称
        private String cabinet_name;//预约门店柜子
        private String cabinet_lattice;//预约门店柜子的格子


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

        public String getNumbers() {
            return numbers;
        }

        public void setNumbers(String numbers) {
            this.numbers = numbers;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getCabinet_name() {
            return cabinet_name;
        }

        public void setCabinet_name(String cabinet_name) {
            this.cabinet_name = cabinet_name;
        }

        public String getCabinet_lattice() {
            return cabinet_lattice;
        }

        public void setCabinet_lattice(String cabinet_lattice) {
            this.cabinet_lattice = cabinet_lattice;
        }
    }
}
