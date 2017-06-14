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

public class VideoList implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Video> mVideos = new ArrayList<>();

    public static final Creator<VideoList> CREATOR = new Creator<VideoList>() {

        @SuppressWarnings({
                "unchecked"
        })
        public VideoList createFromParcel(Parcel in) {
            VideoList instance = new VideoList();
            in.readList(instance.mVideos, (Video.class.getClassLoader()));
            return instance;
        }

        public VideoList[] newArray(int size) {
            return (new VideoList[size]);
        }
    };

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mVideos);
    }

    public int describeContents() {
        return 0;
    }
}
