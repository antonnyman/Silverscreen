package se.k3.antonochisak.silverscreen.api.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import se.k3.antonochisak.silverscreen.models.Ids;
import se.k3.antonochisak.silverscreen.models.Image;

/**
 * Created by anton on 2015-04-13.
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
}
