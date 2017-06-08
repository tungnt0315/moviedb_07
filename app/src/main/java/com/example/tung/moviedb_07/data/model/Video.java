package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/8/17.
 */

public class Video implements Parcelable {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("iso_639_1")
    @Expose
    private String mIso6391;

    @SerializedName("iso_3166_1")
    @Expose
    private String mIso31661;

    @SerializedName("key")
    @Expose
    private String mKey;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("site")
    @Expose
    private String mSite;

    @SerializedName("size")
    @Expose
    private Integer mSize;

    @SerializedName("type")
    @Expose
    private String mType;

    public static final Creator<Video> CREATOR = new Creator<Video>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Video createFromParcel(Parcel in) {
            Video instance = new Video();
            instance.mId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mIso6391 = ((String) in.readValue((String.class.getClassLoader())));
            instance.mIso31661 = ((String) in.readValue((String.class.getClassLoader())));
            instance.mKey = ((String) in.readValue((String.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            instance.mSite = ((String) in.readValue((String.class.getClassLoader())));
            instance.mSize = ((Integer) in.readValue((Integer.class.getClassLoader())));
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

    public String getIso6391() {
        return mIso6391;
    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getIso31661() {
        return mIso31661;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
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

    public Integer getSize() {
        return mSize;
    }

    public void setSize(Integer size) {
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
        dest.writeValue(mIso6391);
        dest.writeValue(mIso31661);
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
