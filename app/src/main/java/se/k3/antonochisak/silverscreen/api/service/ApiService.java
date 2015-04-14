package se.k3.antonochisak.silverscreen.api.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import se.k3.antonochisak.silverscreen.api.model.ApiResponse;

/**
 * Created by anton on 2015-04-13.
 */
public interface ApiService {

    @Headers({
            "Content-type: application/json",
            "trakt-api-key: 6669f7e61b6df49bc8faf9fdaf3f3ddc0185c60d5d18e8305cd2ddc18e10238c",
            "trakt-api-version: 2"

    })
    @GET("/movies/popular")
    void getPopular(@Query("extended") String images,
                    Callback<List<ApiResponse>> callback);

    /*@GET("/api/public/v1.0/lists/movies/box_office.json")
    void getBoxOffice(@Query("limit") int limit,
                      @Query("country") String country,
                      Callback<BoxOfficeResponse> callback);*/

}
