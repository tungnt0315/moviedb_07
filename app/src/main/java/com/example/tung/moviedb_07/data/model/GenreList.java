package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GenreList implements Parcelable {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    public static final Creator<GenreList> CREATOR = new Creator<GenreList>() {

        @SuppressWarnings({ "unchecked" })
        public GenreList createFromParcel(Parcel in) {
            GenreList instance = new GenreList();
            in.readList(instance.genres, (Genre.class.getClassLoader()));
            return instance;
        }

        public GenreList[] newArray(int size) {
            return (new GenreList[size]);
        }
    };

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(genres);
    }

    public int describeContents() {
        return 0;
    }
}
