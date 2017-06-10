package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public class GenreList implements Parcelable {

    @SerializedName("genres")
    @Expose
    private List<Genre> mGenres;

    public static final Creator<GenreList> CREATOR = new Creator<GenreList>() {

        @SuppressWarnings({ "unchecked" })
        public GenreList createFromParcel(Parcel in) {
            GenreList instance = new GenreList();
            in.readList(instance.mGenres, (Genre.class.getClassLoader()));
            return instance;
        }

        public GenreList[] newArray(int size) {
            return (new GenreList[size]);
        }
    };

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mGenres);
    }

    public int describeContents() {
        return 0;
    }
}
