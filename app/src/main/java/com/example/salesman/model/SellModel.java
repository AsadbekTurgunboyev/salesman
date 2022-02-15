package com.example.salesman.model;

public class SellModel {
    String product_name;
    String cat_name;
    String key;
    int id;
    String sotilgan_narx;
    int olingan_miqdor;

    public SellModel(String product_name, String cat_name, String key, int id, String sotilgan_narx, int olingan_narx) {
        this.product_name = product_name;
        this.cat_name = cat_name;
        this.key = key;
        this.id = id;
        this.sotilgan_narx = sotilgan_narx;
        this.olingan_miqdor = olingan_narx;
    }

    public String getProduct_name() {
        return product_name;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSotilgan_narx() {
        return sotilgan_narx;
    }

    public void setSotilgan_narx(String sotilgan_narx) {
        this.sotilgan_narx = sotilgan_narx;
    }

    public int getOlingan_miqdor() {
        return olingan_miqdor;
    }

    public void setOlingan_miqdor(int olingan_miqdor) {
        this.olingan_miqdor = olingan_miqdor;
    }
}
