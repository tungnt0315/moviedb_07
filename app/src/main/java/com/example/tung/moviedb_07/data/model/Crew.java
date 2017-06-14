package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tung on 6/14/17.
 */

public class Crew implements Parcelable {

    @SerializedName("credit_id")
    private String mCreditId;
    @SerializedName("department")
    private String mDepartment;
    @SerializedName("gender")
    private int mGender;
    @SerializedName("id")
    private int mId;
    @SerializedName("job")
    private String mJob;
    @SerializedName("name")
    private String mName;
    @SerializedName("profile_path")
    private String mProfilePath;

    public static final Creator<Crew> CREATOR = new Creator<Crew>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Crew createFromParcel(Parcel in) {
            Crew instance = new Crew();
            instance.mCreditId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mDepartment = ((String) in.readValue((String.class.getClassLoader())));
            instance.mGender = ((int) in.readValue((int.class.getClassLoader())));
            instance.mId = ((int) in.readValue((int.class.getClassLoader())));
            instance.mJob = ((String) in.readValue((String.class.getClassLoader())));
            instance.mName = ((String) in.readValue((String.class.getClassLoader())));
            instance.mProfilePath = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Crew[] newArray(int size) {
            return (new Crew[size]);
        }
    };

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
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

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mCreditId);
        dest.writeValue(mDepartment);
        dest.writeValue(mGender);
        dest.writeValue(mId);
        dest.writeValue(mJob);
        dest.writeValue(mName);
        dest.writeValue(mProfilePath);
    }

    public int describeContents() {
        return 0;
    }
}
