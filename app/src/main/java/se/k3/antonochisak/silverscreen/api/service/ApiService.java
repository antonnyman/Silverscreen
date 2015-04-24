package se.k3.antonochisak.silverscreen.api.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import se.k3.antonochisak.silverscreen.api.model.ApiResponse;
import se.k3.antonochisak.silverscreen.api.model.RootApiResponse;

import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.TRAKT_API_KEY;
import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.TRAKT_API_VERSION;
import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.TRAKT_CONTENT_TYPE;

/**
 * Created by anton on 2015-04-13.
 */
public interface ApiService {

    String limit = "?page=1&limit=40";

    @Headers({TRAKT_CONTENT_TYPE, TRAKT_API_KEY, TRAKT_API_VERSION})
    @GET("/movies/popular")
    void getPopular(@Query("extended") String images, Callback<List<ApiResponse>> callback);

    @Headers({TRAKT_CONTENT_TYPE, TRAKT_API_KEY, TRAKT_API_VERSION})
    @GET("/movies/trending")
    void getTrending(@Query("extended") String images, Callback<List<RootApiResponse>> callback);

}
