package com.example.android.quakereport.model;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthQuakeApi {
    ;

    @GET("fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=150")
    Single<EarthQuakeResponse> getEarthquakes(@Query(value = "starttime") String startTime,
                                              @Query(value = "endtime") String endTime);

}
