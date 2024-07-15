package com.example.ecommerceapps.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ecommerceapps.dao.AccountDao;
import com.example.ecommerceapps.dao.EcommesDao;
import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.model.Converters;
import com.example.ecommerceapps.model.Product;

// adding annotation for our database entities and db version.
@Database(entities = {Account.class}, version = 1)
public abstract class AccountDatabase extends RoomDatabase {

    private static AccountDatabase instance;

    public abstract AccountDao accountDao();

    public static synchronized AccountDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AccountDatabase.class, "account_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
