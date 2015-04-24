package se.k3.antonochisak.silverscreen.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anton on 2015-04-13.
 */
@Parcel
public class Image {

    @SerializedName("poster")
    public Poster poster;

    @SerializedName("fanart")
    public Fanart fanart;

    public Poster getPoster() {
        return poster;
    }

    public Fanart getFanart() {
        return fanart;
    }
}
