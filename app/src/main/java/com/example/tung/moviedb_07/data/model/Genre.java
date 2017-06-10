package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class Genre implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer mId;

    @SerializedName("name")
    @Expose
    private String mName;

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {

        @SuppressWarnings({ "unchecked" })
        public Genre createFromParcel(Parcel in) {
            Genre instance = new Genre();
            instance.mId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Genre[] newArray(int size) {
            return (new Genre[size]);
        }
    };

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mId);
        dest.writeValue(mName);
    }

    public int describeContents() {
        return 0;
    }
}
