package com.example.android.quakereport.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.quakereport.model.EarthQuakeApiService;
import com.example.android.quakereport.model.EarthQuakeResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EarthquakeViewModel extends ViewModel {


    public MutableLiveData<List<EarthQuakeResponse.Feature>> earthquakes = new MutableLiveData<List<EarthQuakeResponse.Feature>>();
    public MutableLiveData<Boolean> earthquakeLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private EarthQuakeApiService earthquakeService = new EarthQuakeApiService();
    private CompositeDisposable disposable = new CompositeDisposable();


    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        loading.setValue(true);
        disposable.add(
                earthquakeService.getEarthquakes()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<EarthQuakeResponse>() {

                            public void onSuccess(List<EarthQuakeResponse.Feature> earthQuakes) {
                                earthquakes.setValue(earthQuakes);
                                earthquakeLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            public void onSuccess(EarthQuakeResponse earthQuakeResponse) {

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

