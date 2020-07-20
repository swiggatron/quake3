/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.EarthQuakeResponse;
import com.example.android.quakereport.viewmodel.EarthquakeViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarthQuakeFragment extends Fragment {


    public EarthQuakeAdapter.EarthquakeViewHolder viewHolder;
    public EarthquakeViewModel viewModel;

    public EarthQuakeAdapter quakeAdapter = new EarthQuakeAdapter(new ArrayList<EarthQuakeResponse.Feature>(), getContext());

    @BindView(R.id.quakeList)
    RecyclerView quakeList;

    @BindView(R.id.listError)
    TextView listError;

    @BindView(R.id.loadingView)
    ProgressBar loadView;


    public EarthQuakeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.earthquake_activity, container, false);

        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //This tells the viewModel where it's getting it's instance from. Even during configuration changes, should the fragment be
        //destroyed and created again, it will go here to get it's saved instance.
        viewModel = ViewModelProviders.of(this).get(EarthquakeViewModel.class);
        viewModel.refresh();
        //This sets the layout for the fragment.(*Lookup* why the need for a layout manager if it's already defined in the XML?)
        quakeList.setLayoutManager(new LinearLayoutManager(getContext()));
        //This sets the adapter
        quakeList.setAdapter(quakeAdapter);

        observeViewModel();


    }

    private void observeViewModel() {
        viewModel.earthquakes.observe(this, new Observer<List<EarthQuakeResponse.Feature>>() {
            @Override
            public void onChanged(List<EarthQuakeResponse.Feature> earthquakes) {
                if (earthquakes != null) {
                    quakeList.setVisibility(View.VISIBLE);
                    quakeAdapter.updateEarthquakes(earthquakes);
                }
            }
        });
        viewModel.earthquakeLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if (isError != null) ;
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);


            }
        });
        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading != null) {
                    loadView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    if (isLoading) {
                        listError.setVisibility(View.GONE);
                        quakeList.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

}





