package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class ProductionCompany implements Parcelable {

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("id")
    @Expose
    private Integer mId;

    public static final Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {

        @SuppressWarnings({ "unchecked" })
        public ProductionCompany createFromParcel(Parcel in) {
            ProductionCompany instance = new ProductionCompany();
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            instance.mId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public ProductionCompany[] newArray(int size) {
            return (new ProductionCompany[size]);
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mName);
        dest.writeValue(mId);
    }

    public int describeContents() {
        return 0;
    }
}
