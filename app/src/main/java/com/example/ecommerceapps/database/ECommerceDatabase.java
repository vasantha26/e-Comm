package com.example.ecommerceapps.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ecommerceapps.dao.EcommesDao;
import com.example.ecommerceapps.model.Converters;
import com.example.ecommerceapps.model.Product;

// adding annotation for our database entities and db version.
@Database(entities = {Product.class}, version = 4)
@TypeConverters(Converters.class)
public abstract class ECommerceDatabase extends RoomDatabase {

    private static ECommerceDatabase instance;

    public abstract EcommesDao ecommesDao();

    public static synchronized ECommerceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ECommerceDatabase.class, "ecomm_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
