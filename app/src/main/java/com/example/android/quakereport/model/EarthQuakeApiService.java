package com.example.android.quakereport.model;

import com.example.android.quakereport.view.FrontPageFragment;

import java.text.ParseException;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthQuakeApiService {

    public final String BASE_URL = "https://earthquake.usgs.gov/";


    FrontPageFragment input = new FrontPageFragment();
    String startTime = input.getStartTime();
    String endTime = input.getEndTime();

    EarthQuakeApi api;

    public EarthQuakeApiService() throws ParseException {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EarthQuakeApi.class);
    }

    public Single<EarthQuakeResponse> getEarthquakes() {
        return api.getEarthquakes(startTime, endTime);
    }
}
