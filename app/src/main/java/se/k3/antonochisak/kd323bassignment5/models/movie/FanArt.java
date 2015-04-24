package se.k3.antonochisak.kd323bassignment5.models.movie;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by isak on 2015-04-24.
 */

@Parcel
public class FanArt {

    @SerializedName("full")
    public String fullFanArt;
    @SerializedName("medium")
    public String mediumFanArt;
    @SerializedName("thumb")
    public String thumbFanArt;

    @ParcelConstructor
    public FanArt(String fullFanArt, String mediumFanArt, String thumbFanArt) {
        this.fullFanArt = fullFanArt;
        this.mediumFanArt = mediumFanArt;
        this.thumbFanArt = thumbFanArt;
    }

    public String getFullFanArt() {
        return fullFanArt;
    }

    public String getMediumFanArt() {
        return mediumFanArt;
    }

    public String getThumbFanArt() {
        return thumbFanArt;
    }
}