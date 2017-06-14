package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class Video implements Parcelable {

    @SerializedName("id")
    private String mId;
    @SerializedName("key")
    private String mKey;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("size")
    private int mSize;
    @SerializedName("type")
    private String mType;

    public static final Creator<Video> CREATOR = new Creator<Video>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Video createFromParcel(Parcel in) {
            Video instance = new Video();
            instance.mId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mKey = ((String) in.readValue((String.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            instance.mSite = ((String) in.readValue((String.class.getClassLoader())));
            instance.mSize = ((int) in.readValue((int.class.getClassLoader())));
            instance.mType = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Video[] newArray(int size) {
            return (new Video[size]);
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mId);
        dest.writeValue(mKey);
        dest.writeValue(mName);
        dest.writeValue(mSite);
        dest.writeValue(mSize);
        dest.writeValue(mType);
    }

    public int describeContents() {
        return 0;
    }
}
