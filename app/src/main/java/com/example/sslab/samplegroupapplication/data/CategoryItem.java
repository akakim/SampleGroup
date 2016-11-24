package com.example.sslab.samplegroupapplication.data;

/**
 * Created by SSLAB on 2016-11-24.
 */

public class CategoryItem {

    private String order;
    private String category;
    private String subCategory;
    private String body;


    public CategoryItem(String order, String category, String subCategory, String body) {
        this.order = order;
        this.category = category;
        this.subCategory = subCategory;
        this.body = body;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return order +". category : " +category + " subCategory : "+ subCategory+ " body : "+ body;
    }
}
