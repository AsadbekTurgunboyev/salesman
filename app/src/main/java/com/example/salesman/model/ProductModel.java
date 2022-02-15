package com.example.salesman.model;

public class ProductModel {
    String product_name;
    String cat_name;
    String key;
    int olingan_narx;
    int sotilgan_narx;
    int mavjud_qiymat;

    public ProductModel(String product_name,String key, String cat_name, int olingan_narx, int sotilgan_narx, int mavjud_qiymat) {
        this.product_name = product_name;
        this.cat_name = cat_name;
        this.key = key;
        this.olingan_narx = olingan_narx;
        this.sotilgan_narx = sotilgan_narx;
        this.mavjud_qiymat = mavjud_qiymat;
    }

    public ProductModel() {
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getOlingan_narx() {
        return olingan_narx;
    }

    public void setOlingan_narx(int olingan_narx) {
        this.olingan_narx = olingan_narx;
    }

    public int getSotilgan_narx() {
        return sotilgan_narx;
    }

    public void setSotilgan_narx(int sotilgan_narx) {
        this.sotilgan_narx = sotilgan_narx;
    }

    public int getMavjud_qiymat() {
        return mavjud_qiymat;
    }

    public void setMavjud_qiymat(int mavjud_qiymat) {
        this.mavjud_qiymat = mavjud_qiymat;
    }
}
