package se.k3.antonochisak.kd323bassignment5.api.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import se.k3.antonochisak.kd323bassignment5.models.movie.Ids;
import se.k3.antonochisak.kd323bassignment5.models.movie.Image;

/**
 * Created by isak on 2015-04-24.
 */

@Parcel
public class ApiResponse {

    @SerializedName("title")
    public String title;

    @SerializedName("ids")
    public Ids ids;

    @SerializedName("year")
    public int year;

    @SerializedName("images")
    public Image image;

    @SerializedName("overview")
    public String overview;

    @SerializedName("tagline")
    public String tagline;
}