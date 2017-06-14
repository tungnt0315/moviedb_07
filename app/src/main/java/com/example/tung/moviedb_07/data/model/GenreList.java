package com.example.tung.moviedb_07.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public class GenreList {

    @SerializedName("genres")
    private List<Genre> mGenres = new ArrayList<>();

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }
}
