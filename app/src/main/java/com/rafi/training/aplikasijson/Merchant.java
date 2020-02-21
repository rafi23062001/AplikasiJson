package com.rafi.training.aplikasijson;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant implements Parcelable {
    private int merchantID;
    private String merchantname;
    private String merchantSlug;

    public Merchant(int merchantID, String merchantname, String merchantSlug) {
        this.merchantID = merchantID;
        this.merchantname = merchantname;
        this.merchantSlug = merchantSlug;
    }


    public int getMerchantID() {
        return merchantID;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public String getMerchantSlug() {
        return merchantSlug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.merchantID);
        dest.writeString(this.merchantname);
        dest.writeString(this.merchantSlug);
    }

    protected Merchant(Parcel in) {
        this.merchantID = in.readInt();
        this.merchantname = in.readString();
        this.merchantSlug = in.readString();
    }

    public static final Creator<Merchant> CREATOR = new Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel source) {
            return new Merchant(source);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };
}
