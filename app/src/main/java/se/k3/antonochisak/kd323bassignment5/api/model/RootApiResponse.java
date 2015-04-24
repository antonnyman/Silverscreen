package se.k3.antonochisak.kd323bassignment5.api.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by isak on 2015-04-24.
 */

@Parcel
public class RootApiResponse {

    @SerializedName("movie")
    public ApiResponse apiResponse;
}