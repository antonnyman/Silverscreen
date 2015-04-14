package se.k3.antonochisak.silverscreen.api.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

import se.k3.antonochisak.silverscreen.models.Image;

/**
 * Created by anton on 2015-04-13.
 */
@Parcel
public class ApiResponse {

    @SerializedName("title")
    public String title;

    @SerializedName("slugline")
    public String slugline;

    @SerializedName("year")
    public int year;

    @SerializedName("images")
    public Image image;

    public String getTitle() {
        return title;
    }

    public String getSlugline() {
        return slugline;
    }

    public int getYear() {
        return year;
    }

    public Image getImage() {
        return image;
    }


}
