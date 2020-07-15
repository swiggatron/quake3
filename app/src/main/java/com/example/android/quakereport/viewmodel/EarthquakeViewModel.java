package com.example.android.quakereport.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.quakereport.model.EarthQuakeApiService;
import com.example.android.quakereport.model.EarthQuakeResponse;

import java.text.ParseException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EarthquakeViewModel extends ViewModel {

    private MutableLiveData<String> startTime = new MutableLiveData<>();
    private MutableLiveData<String> endTime = new MutableLiveData<>();
    public MutableLiveData<List<EarthQuakeResponse.Feature>> earthquakes = new MutableLiveData<List<EarthQuakeResponse.Feature>>();
    public MutableLiveData<Boolean> earthquakeLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private EarthQuakeApiService earthquakeService = new EarthQuakeApiService();
    private CompositeDisposable disposable = new CompositeDisposable();


    public EarthquakeViewModel() throws ParseException {
    }

    public void setStartTime(String input) {
        startTime.setValue(input);
    }

    public LiveData<String> getStartTime() {
        return startTime;
    }

    public void setEndTime(String input) {
        endTime.setValue(input);
    }

    public LiveData<String> getEndTime() {
        return endTime;
    }

    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        loading.setValue(true);
        disposable.add(
                earthquakeService.getEarthquakes(startTime, endTime)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<EarthQuakeResponse>() {

                            public void onSuccess(EarthQuakeResponse earthQuakeResponse) {
                                earthquakes.setValue(earthQuakeResponse.getFeatures());
                                earthquakeLoadError.setValue(false);
                                loading.setValue(false);
                            }


                            @Override
                            public void onError(Throwable e) {
                                earthquakeLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();

                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}

