package com.example.ecommerceapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ecommerceapps.model.Product;

import java.util.List;

@Dao
public interface EcommesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);



    @Query("SELECT * FROM ecomm_table LIMIT 1, 5")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM ecomm_table ORDER BY title ASC")
    LiveData<List<Product>> getAllProductsItem();



}
