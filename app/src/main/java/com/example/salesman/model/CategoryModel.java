package com.example.salesman.model;

import java.util.List;

public class CategoryModel {
    String category;
    int id;

    public CategoryModel(String category, int id) {
        this.category = category;
        this.id = id;

    }

    public CategoryModel() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
