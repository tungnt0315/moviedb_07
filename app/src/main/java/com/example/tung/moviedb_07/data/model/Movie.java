package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 6/8/17.
 */

public class Movie implements Parcelable {

    @SerializedName("adult")
    @Expose
    private Boolean mAdult;

    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;

    @SerializedName("belongs_to_collection")
    @Expose
    private Object mBelongsToCollection;

    @SerializedName("budget")
    @Expose
    private Integer mBudget;

    @SerializedName("genres")
    @Expose
    private List<Genre> mGenres = new ArrayList<>();

    @SerializedName("homepage")
    @Expose
    private String mHomepage;

    @SerializedName("id")
    @Expose
    private Integer mId;

    @SerializedName("imdb_id")
    @Expose
    private String mImdbId;

    @SerializedName("original_language")
    @Expose
    private String mOriginalLanguage;

    @SerializedName("original_title")
    @Expose
    private String mOriginalTitle;

    @SerializedName("overview")
    @Expose
    private String mOverview;

    @SerializedName("popularity")
    @Expose
    private Double mPopularity;

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> mProductionCompanies = new ArrayList<>();

    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountry> mProductionCountries = new ArrayList<>();

    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;

    @SerializedName("revenue")
    @Expose
    private Integer mRevenue;

    @SerializedName("runtime")
    @Expose
    private Integer mRuntime;

    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> mSpokenLanguages = new ArrayList<>();

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("tagline")
    @Expose
    private String mTagline;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("video")
    @Expose
    private Boolean mVideo;

    @SerializedName("vote_average")
    @Expose
    private Double mVoteAverage;

    @SerializedName("vote_count")
    @Expose
    private Integer mVoteCount;

    @SerializedName("videos")
    @Expose
    private VideoList mVideoList;

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            Movie instance = new Movie();
            instance.mAdult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.mBackdropPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.mBelongsToCollection =
                    ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mBudget = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.mGenres, (Genre.class.getClassLoader()));
            instance.mHomepage = ((String) in.readValue((String.class.getClassLoader())));
            instance.mId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.mImdbId = ((String) in.readValue((String.class.getClassLoader())));
            instance.mOriginalLanguage = ((String) in.readValue((String.class.getClassLoader())));
            instance.mOriginalTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.mOverview = ((String) in.readValue((String.class.getClassLoader())));
            instance.mPopularity = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.mPosterPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.mProductionCompanies, (ProductionCompany.class.getClassLoader()));
            in.readList(instance.mProductionCountries, (ProductionCountry.class.getClassLoader()));
            instance.mReleaseDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.mRevenue = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.mRuntime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.mSpokenLanguages, (SpokenLanguage.class.getClassLoader()));
            instance.mStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.mTagline = ((String) in.readValue((String.class.getClassLoader())));
            instance.mTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.mVideo = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.mVoteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.mVoteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.mVideoList = ((VideoList) in.readValue((VideoList.class.getClassLoader())));
            return instance;
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }
    };

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Object getBelongsToCollection() {
        return mBelongsToCollection;
    }

    public void setBelongsToCollection(Object belongsToCollection) {
        mBelongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return mBudget;
    }

    public void setBudget(Integer budget) {
        mBudget = budget;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
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

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
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

    public Integer getRevenue() {
        return mRevenue;
    }

    public void setRevenue(Integer revenue) {
        mRevenue = revenue;
    }

    public Integer getRuntime() {
        return mRuntime;
    }

    public void setRuntime(Integer runtime) {
        mRuntime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return mSpokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        mSpokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Integer voteCount) {
        mVoteCount = voteCount;
    }

    public VideoList getVideoList() {
        return mVideoList;
    }

    public void setVideoList(VideoList videoList) {
        mVideoList = videoList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mAdult);
        dest.writeValue(mBackdropPath);
        dest.writeValue(mBelongsToCollection);
        dest.writeValue(mBudget);
        dest.writeList(mGenres);
        dest.writeValue(mHomepage);
        dest.writeValue(mId);
        dest.writeValue(mImdbId);
        dest.writeValue(mOriginalLanguage);
        dest.writeValue(mOriginalTitle);
        dest.writeValue(mOverview);
        dest.writeValue(mPopularity);
        dest.writeValue(mPosterPath);
        dest.writeList(mProductionCompanies);
        dest.writeList(mProductionCountries);
        dest.writeValue(mReleaseDate);
        dest.writeValue(mRevenue);
        dest.writeValue(mRuntime);
        dest.writeList(mSpokenLanguages);
        dest.writeValue(mStatus);
        dest.writeValue(mTagline);
        dest.writeValue(mTitle);
        dest.writeValue(mVideo);
        dest.writeValue(mVoteAverage);
        dest.writeValue(mVoteCount);
        dest.writeValue(mVideoList);
    }

    public int describeContents() {
        return 0;
    }
}
