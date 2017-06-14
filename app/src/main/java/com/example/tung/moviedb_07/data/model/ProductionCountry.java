package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class ProductionCountry implements Parcelable {

    @SerializedName("iso_3166_1")
    private String mIso31661;
    @SerializedName("name")
    private String mName;

    public static final Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ProductionCountry createFromParcel(Parcel in) {
            ProductionCountry instance = new ProductionCountry();
            instance.mIso31661 = ((String) in.readValue((String.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ProductionCountry[] newArray(int size) {
            return (new ProductionCountry[size]);
        }
    };

    public String getIso31661() {
        return mIso31661;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mIso31661);
        dest.writeValue(mName);
    }

    public int describeContents() {
        return 0;
    }
}
