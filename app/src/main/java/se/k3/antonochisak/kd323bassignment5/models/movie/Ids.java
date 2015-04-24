package se.k3.antonochisak.kd323bassignment5.models.movie;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by isak on 2015-04-24.
 */
@Parcel
public class Ids {

    @SerializedName("slug")
    public String slug;

    public String getSlug() {
        return slug;
    }
}