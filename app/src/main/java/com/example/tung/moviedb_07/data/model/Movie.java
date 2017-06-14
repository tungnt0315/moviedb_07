package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public class Movie implements Parcelable {

    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("genres")
    private List<Genre> mGenres = new ArrayList<>();
    @SerializedName("id")
    private int mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies = new ArrayList<>();
    @SerializedName("production_countries")
    private List<ProductionCountry> mProductionCountries = new ArrayList<>();
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vote_average")
    private double mVoteAverage;
    @SerializedName("vote_count")
    private int mVoteCount;
    @SerializedName("videos")
    private VideoList mVideoList;
    @SerializedName("credits")
    private Credits mCredits;

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            Movie instance = new Movie();
            instance.mBackdropPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.mGenres, (Genre.class.getClassLoader()));
            instance.mId = ((int) in.readValue((int.class.getClassLoader())));
            instance.mOriginalLanguage = ((String) in.readValue((String.class.getClassLoader())));
            instance.mOriginalTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.mOverview = ((String) in.readValue((String.class.getClassLoader())));
            instance.mPopularity = ((double) in.readValue((double.class.getClassLoader())));
            instance.mPosterPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.mProductionCompanies, (ProductionCompany.class.getClassLoader()));
            in.readList(instance.mProductionCountries, (ProductionCountry.class.getClassLoader()));
            instance.mReleaseDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.mStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.mTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.mVoteAverage = ((double) in.readValue((double.class.getClassLoader())));
            instance.mVoteCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.mVideoList = ((VideoList) in.readValue((VideoList.class.getClassLoader())));
            instance.mCredits = ((Credits) in.readValue((Credits.class.getClassLoader())));
            return instance;
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }
    };

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return mProductionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        mProductionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public VideoList getVideoList() {
        return mVideoList;
    }

    public void setVideoList(VideoList videoList) {
        mVideoList = videoList;
    }

    public Credits getCredits() {
        return mCredits;
    }

    public void setCredits(Credits credits) {
        mCredits = credits;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mBackdropPath);
        dest.writeList(mGenres);
        dest.writeValue(mId);
        dest.writeValue(mOriginalLanguage);
        dest.writeValue(mOriginalTitle);
        dest.writeValue(mOverview);
        dest.writeValue(mPopularity);
        dest.writeValue(mPosterPath);
        dest.writeList(mProductionCompanies);
        dest.writeList(mProductionCountries);
        dest.writeValue(mReleaseDate);
        dest.writeValue(mStatus);
        dest.writeValue(mTitle);
        dest.writeValue(mVoteAverage);
        dest.writeValue(mVoteCount);
        dest.writeValue(mVideoList);
        dest.writeValue(mCredits);
    }

    public int describeContents() {
        return 0;
    }
}
