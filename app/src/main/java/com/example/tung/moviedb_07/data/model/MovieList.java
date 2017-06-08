package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieList implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_results")
    @Expose
    private Integer totalMovies;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("movies")
    @Expose
    private List<Movie> movies = null;

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {

        @SuppressWarnings({ "unchecked" })
        public MovieList createFromParcel(Parcel in) {
            MovieList instance = new MovieList();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.movies, (Movie.class.getClassLoader()));
            return instance;
        }

        public MovieList[] newArray(int size) {
            return (new MovieList[size]);
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalMovies);
        dest.writeValue(totalPages);
        dest.writeList(movies);
    }

    public int describeContents() {
        return 0;
    }
}
