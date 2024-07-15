package com.example.ecommerceapps.retrofit;

import com.example.ecommerceapps.model.Category;
import com.example.ecommerceapps.model.Product;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products")
    Call<List<Product>> getProductsLimits(@Query("limit") int limit);

    @GET("products/categories")
    Call<List<String>> getCategories();

    @GET("products/category/jewelery")
    Call<List<Product>> getJeweleryCategory();

    @GET("products/category/electronics")
    Call<List<Product>> getElectronicsCategory();

    @GET("products/category/men's clothing")
    Call<List<Product>> getMenSpecificCategory();


    @GET("products/category/women's clothing")
    Call<List<Product>> getWomenSpecificCategory();




}
