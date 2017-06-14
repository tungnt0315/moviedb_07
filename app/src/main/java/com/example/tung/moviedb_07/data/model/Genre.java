package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class Genre implements Parcelable {

    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Genre createFromParcel(Parcel in) {
            Genre instance = new Genre();
            instance.mId = ((int) in.readValue((int.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Genre[] newArray(int size) {
            return (new Genre[size]);
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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
