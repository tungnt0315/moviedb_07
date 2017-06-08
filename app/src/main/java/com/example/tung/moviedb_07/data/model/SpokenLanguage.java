package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class SpokenLanguage implements Parcelable {

    @SerializedName("iso_639_1")
    @Expose
    private String mIso6391;

    @SerializedName("name")
    @Expose
    private String mName;

    public static final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {

        @SuppressWarnings({ "unchecked" })
        public SpokenLanguage createFromParcel(Parcel in) {
            SpokenLanguage instance = new SpokenLanguage();
            instance.mIso6391 = ((String) in.readValue((String.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public SpokenLanguage[] newArray(int size) {
            return (new SpokenLanguage[size]);
        }
    };

    public String getIso6391() {
        return mIso6391;
    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mIso6391);
        dest.writeValue(mName);
    }

    public int describeContents() {
        return 0;
    }
}
