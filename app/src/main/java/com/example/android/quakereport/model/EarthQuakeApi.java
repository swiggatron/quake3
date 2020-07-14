package com.example.android.quakereport.model;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EarthQuakeApi {

    @GET("fdsnws/event/1/query?format=geojson&eventtype=earthquake&starttime={starttime}&endtime={endtime}&orderby=time&minmag=6&limit=20")
    Single<EarthQuakeResponse> getEarthquakes(@Path("starttime") String startTime, @Path("endtime") String endTime);

}
