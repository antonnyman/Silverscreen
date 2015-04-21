package se.k3.antonochisak.silverscreen.api.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by anton on 2015-04-18.
 */
@Parcel
public class RootApiResponse {

    @SerializedName("movie")
    public ApiResponse apiResponse;
}
