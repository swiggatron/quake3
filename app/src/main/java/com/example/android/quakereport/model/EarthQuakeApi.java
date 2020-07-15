package com.example.android.quakereport.model;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthQuakeApi {
    ;

    @GET("fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=20")
    Single<EarthQuakeResponse> getEarthquakes(@Query(value = "starttime") MutableLiveData<String> startTime,
                                              @Query(value = "endtime") MutableLiveData<String> endTime);

}
