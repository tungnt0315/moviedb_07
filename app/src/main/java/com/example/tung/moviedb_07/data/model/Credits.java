package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/14/17.
 */

public class Credits implements Parcelable {

    @SerializedName("cast")
    private List<Cast> mCasts = new ArrayList<>();
    @SerializedName("crew")
    private List<Crew> mCrews = new ArrayList<>();

    public static final Creator<Credits> CREATOR = new Creator<Credits>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Credits createFromParcel(Parcel in) {
            Credits instance = new Credits();
            in.readList(instance.mCasts, (Cast.class.getClassLoader()));
            in.readList(instance.mCrews, (Crew.class.getClassLoader()));
            return instance;
        }

        public Credits[] newArray(int size) {
            return (new Credits[size]);
        }
    };

    public List<Cast> getCasts() {
        return mCasts;
    }

    public void setCasts(List<Cast> casts) {
        mCasts = casts;
    }

    public List<Crew> getCrews() {
        return mCrews;
    }

    public void setCrews(List<Crew> crews) {
        mCrews = crews;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mCasts);
        dest.writeList(mCrews);
    }

    public int describeContents() {
        return 0;
    }
}
