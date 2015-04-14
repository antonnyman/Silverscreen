package se.k3.antonochisak.silverscreen.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anton on 2015-04-14.
 */
@Parcel
public class Fanart {

    @SerializedName("full")
    public String fullFanart;

    @SerializedName("medium")
    public String mediumFanart;

    @SerializedName("thumb")
    public String thumbFanart;

    public String getFullFanart() {
        return fullFanart;
    }

    public String getMediumFanart() {
        return mediumFanart;
    }

    public String getThumbFanart() {
        return thumbFanart;
    }
}
