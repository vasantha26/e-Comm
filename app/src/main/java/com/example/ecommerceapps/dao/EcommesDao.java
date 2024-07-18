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

    @Query("UPDATE ecomm_table SET isFavorite = :isFav WHERE id = :id")
    void updateIsFav(int id, boolean isFav);

    @Query("UPDATE ecomm_table SET isCart = :isCart WHERE id = :id")
    void updateCart(int id, boolean isCart);

}
