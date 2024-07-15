package com.example.ecommerceapps.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity(tableName = "ecomm_table")
public class Product implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private double price;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;
    @SerializedName("image")
    private String image;
    @SerializedName("rating")
    @TypeConverters(Converters.class)
    private Rating rating;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        price = in.readDouble();
        description = in.readString();
        category = in.readString();
        image = in.readString();
        rating = in.readParcelable(Rating.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeString(image);
        dest.writeParcelable(rating, flags);
    }
}




