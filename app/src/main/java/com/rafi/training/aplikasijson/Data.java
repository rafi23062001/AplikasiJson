package com.rafi.training.aplikasijson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {
    int productID;
    String productname;
    String productSlug;
    int productQty;
    String productimage;
    Merchant merchant;
    Category category;

    public Data(long productID, String productname, String productSlug, int productQty, String productimage, Merchant merchant, Category category) {
        this.productID = (int) productID;
        this.productname = productname;
        this.productSlug = productSlug;
        this.productQty = productQty;
        this.productimage = productimage;
        this.merchant = merchant;
        this.category = category;
    }
    public int getProductID() {
        return productID;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public int getProductQty() {
        return productQty;
    }

    public String getProductimage() {
        return productimage;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productID);
        dest.writeString(this.productname);
        dest.writeString(this.productSlug);
        dest.writeInt(Integer.parseInt(String.valueOf(this.productQty)));
        dest.writeString(this.productimage);
        dest.writeParcelable(this.merchant, flags);
        dest.writeParcelable(this.category, flags);
    }

    protected Data(Parcel in) {
        this.productID = in.readInt();
        this.productname = in.readString();
        this.productSlug = in.readString();
        this.productQty = in.readInt();
        this.productimage = in.readString();
        this.merchant = in.readParcelable(Merchant.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
