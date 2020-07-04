package com.example.android.quakereport.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface EarthQuakeApi {

    @GET("fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10")
    Single<List<Properties>> getEarthquakes();
}
