package se.k3.antonochisak.silverscreen.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by anton on 2015-04-14.
 */
@Parcel
public class Fanart {

    @ParcelConstructor
    public Fanart(String fullFanart, String mediumFanart, String thumbFanart) {
        this.fullFanart = fullFanart;
        this.mediumFanart = mediumFanart;
        this.thumbFanart = thumbFanart;
    }

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
