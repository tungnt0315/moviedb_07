package com.example.tung.moviedb_07.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VideoList implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Video> videos = null;

    public static final Creator<VideoList> CREATOR = new Creator<VideoList>() {

        @SuppressWarnings({
                "unchecked"
        })
        public VideoList createFromParcel(Parcel in) {
            VideoList instance = new VideoList();
            in.readList(instance.videos, (Video.class.getClassLoader()));
            return instance;
        }

        public VideoList[] newArray(int size) {
            return (new VideoList[size]);
        }
    };

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(videos);
    }

    public int describeContents() {
        return 0;
    }
}
