package se.k3.antonochisak.silverscreen.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by anton on 2015-04-13.
 */
@Parcel
public class Poster {

    @SerializedName("full")
    public String fullPoster;
    @SerializedName("medium")
    public String mediumPoster;
    @SerializedName("thumb")
    public String thumbPoster;

    @ParcelConstructor
    public Poster(String fullPoster, String mediumPoster, String thumbPoster) {
        this.fullPoster = fullPoster;
        this.mediumPoster = mediumPoster;
        this.thumbPoster = thumbPoster;
    }

    public String getFullPoster() {
        return fullPoster;
    }

    public String getMediumPoster() {
        return mediumPoster;
    }

    public String getThumbPoster() {
        return thumbPoster;
    }
}
