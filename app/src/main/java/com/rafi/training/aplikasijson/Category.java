package com.rafi.training.aplikasijson;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private int categoryId;
    private String categoryname;

    public Category(int categoryId, String categoryname) {
        this.categoryId = categoryId;
        this.categoryname = categoryname;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryname() {
        return categoryname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.categoryId);
        dest.writeString(this.categoryname);
    }

    protected Category(Parcel in) {
        this.categoryId = in.readInt();
        this.categoryname = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
