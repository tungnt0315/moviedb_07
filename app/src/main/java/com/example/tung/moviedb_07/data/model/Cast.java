package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/14/17.
 */

public class Cast implements Parcelable {

    @SerializedName("cast_id")
    private int mCastId;
    @SerializedName("character")
    private String mCharacter;
    @SerializedName("credit_id")
    private String mCreditId;
    @SerializedName("gender")
    private int mGender;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("order")
    private int mOrder;
    @SerializedName("profile_path")
    private String mProfilePath;

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Cast createFromParcel(Parcel in) {
            Cast instance = new Cast();
            instance.mCastId = ((int) in.readValue((int.class.getClassLoader())));
            instance.mCharacter = ((String) in.readValue((String.class.getClassLoader())));
            instance.mCreditId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mGender = ((int) in.readValue((int.class.getClassLoader())));
            instance.mId = ((int) in.readValue((int.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            instance.mOrder = ((int) in.readValue((int.class.getClassLoader())));
            instance.mProfilePath = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Cast[] newArray(int size) {
            return (new Cast[size]);
        }
    };

    public int getCastId() {
        return mCastId;
    }

    public void setCastId(int castId) {
        mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

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

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mCastId);
        dest.writeValue(mCharacter);
        dest.writeValue(mCreditId);
        dest.writeValue(mGender);
        dest.writeValue(mId);
        dest.writeValue(mName);
        dest.writeValue(mOrder);
        dest.writeValue(mProfilePath);
    }

    public int describeContents() {
        return 0;
    }
}
