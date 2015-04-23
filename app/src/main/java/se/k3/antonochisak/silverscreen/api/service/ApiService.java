package se.k3.antonochisak.silverscreen.api.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import se.k3.antonochisak.silverscreen.api.model.ApiResponse;
import se.k3.antonochisak.silverscreen.api.model.RootApiResponse;
import se.k3.antonochisak.silverscreen.helpers.StaticHelpers;

import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.TRAKT_API_KEY;
import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.TRAKT_API_VERSION;
import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.TRAKT_CONTENT_TYPE;

/**
 * Created by anton on 2015-04-13.
 */
public interface ApiService {

//    For testing in cURL
//    curl --include \
//            --header "Content-Type: application/json" \
//            --header "trakt-api-version: 2" \
//            --header "trakt-api-key: 6669f7e61b6df49bc8faf9fdaf3f3ddc0185c60d5d18e8305cd2ddc18e10238c" \
//            'https://api-v2launch.trakt.tv/movies/trending'

    @Headers({TRAKT_CONTENT_TYPE, TRAKT_API_KEY, TRAKT_API_VERSION})
    @GET("/movies/popular")
    void getPopular(@Query("extended") String images, Callback<List<ApiResponse>> callback);

/*    @Headers({TRAKT_CONTENT_TYPE, TRAKT_API_KEY, TRAKT_API_VERSION})
    @GET("/movies/trending?page=1&limit=40")
    void getTrending(@Query("extended") String images, Callback<List<RootApiResponse>> callback);*/

    @Headers({TRAKT_CONTENT_TYPE, TRAKT_API_KEY, TRAKT_API_VERSION})
    @GET("/calendars/all/movies/{date}/{days}")
    void getGetUpcoming(@Query("extended") String images, @Path("date") String date, @Path("days") int days, Callback<List<RootApiResponse>> callback);

    // What is difference between RootApiResponse and ApiResponse?
    // Maybe in the json parsing there is something like an @optional annotation?
}
