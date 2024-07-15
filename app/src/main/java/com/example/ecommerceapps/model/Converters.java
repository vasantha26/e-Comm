package com.example.ecommerceapps.model;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static Rating fromString(String value) {
        Type type = new TypeToken<Rating>() {}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromRating(Rating rating) {
        Gson gson = new Gson();
        return gson.toJson(rating);
    }
}
