package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public class MovieList implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer mPage;

    @SerializedName("total_results")
    @Expose
    private Integer mTotalMovies;

    @SerializedName("total_pages")
    @Expose
    private Integer mTotalPages;

    @SerializedName("movies")
    @Expose
    private List<Movie> mMovies;

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {

        @SuppressWarnings({ "unchecked" })
        public MovieList createFromParcel(Parcel in) {
            MovieList instance = new MovieList();
            instance.mPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.mTotalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.mTotalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.mMovies, (Movie.class.getClassLoader()));
            return instance;
        }

        public MovieList[] newArray(int size) {
            return (new MovieList[size]);
        }
    };

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }

    public Integer getTotalMovies() {
        return mTotalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        mTotalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        mTotalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mPage);
        dest.writeValue(mTotalMovies);
        dest.writeValue(mTotalPages);
        dest.writeList(mMovies);
    }

    public int describeContents() {
        return 0;
    }
}
