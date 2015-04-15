package se.k3.antonochisak.silverscreen.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anton on 2015-04-15.
 */
@Parcel
public class Ids {

    @SerializedName("slug")
    public String slug;

    public String getSlug() {
        return slug;
    }
}
