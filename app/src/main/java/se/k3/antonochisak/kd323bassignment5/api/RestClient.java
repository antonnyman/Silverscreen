package se.k3.antonochisak.kd323bassignment5.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import se.k3.antonochisak.kd323bassignment5.api.service.ApiService;

import static se.k3.antonochisak.kd323bassignment5.helpers.StaticHelpers.API_URL;

/**
 * Created by isak on 2015-04-24.
 */

public class RestClient {

    private ApiService apiService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}