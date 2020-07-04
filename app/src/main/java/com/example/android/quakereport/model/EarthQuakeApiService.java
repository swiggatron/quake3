package com.example.android.quakereport.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthQuakeApiService {

    public final String BASE_URL = "https://earthquake.usgs.gov/";

    EarthQuakeApi api;

    public EarthQuakeApiService() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EarthQuakeApi.class);
    }

    public Single<List<Properties>> getEarthquakes() {
        return api.getEarthquakes();
    }
}
