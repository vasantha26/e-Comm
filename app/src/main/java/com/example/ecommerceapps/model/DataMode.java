package com.example.ecommerceapps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataMode {

    @SerializedName("success")
    private boolean success;
    @SerializedName("products")
    private List<Product> products;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
