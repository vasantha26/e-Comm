package com.example.ecommerceapps.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.model.Product;

import java.util.List;

@Dao
public interface AccountDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account account);

    @Query("SELECT * FROM account_table")
    LiveData<List<Account>> getAllAccount();

    @Delete
    void delete(Account product);
}
