package com.example.android.quakereport.model;

import androidx.lifecycle.MutableLiveData;

import java.text.ParseException;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthQuakeApiService {

    public final String BASE_URL = "https://earthquake.usgs.gov/";


    EarthQuakeApi api;

    public EarthQuakeApiService() throws ParseException {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EarthQuakeApi.class);
    }

    public Single<EarthQuakeResponse> getEarthquakes(MutableLiveData<String> startTime, MutableLiveData<String> endTime) {
        return api.getEarthquakes(startTime, endTime);
    }
}
