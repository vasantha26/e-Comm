package com.example.ecommerceapps.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Rating implements Parcelable  {
    @SerializedName("rate")
    private double rate;
    @SerializedName("count")
    private int count;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    protected Rating(Parcel in) {
        rate = in.readDouble();
        count = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(rate);
        dest.writeInt(count);
    }

}