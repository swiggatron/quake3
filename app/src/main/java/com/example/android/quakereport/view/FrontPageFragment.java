package com.example.android.quakereport.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.android.quakereport.R;
import com.example.android.quakereport.viewmodel.EarthquakeViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FrontPageFragment extends Fragment {


    private EarthquakeViewModel viewModel;
    String startTime;
    String endTime;
    MaterialDatePicker.Builder<Long> builder;
    MaterialDatePicker<Long> startPicker;
    MaterialDatePicker<Long> endPicker;
    TextView start;
    TextView end;
    TextView startDate;
    TextView endDate;
    @BindView(R.id.search_button)
    Button search;
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    public FrontPageFragment() {
        //required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(EarthquakeViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.earthquake_frontpage, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        builder = MaterialDatePicker.Builder.datePicker();
        startPicker = builder.build();
        endPicker = builder.build();


        start = getView().findViewById(R.id.startDate);
        end = getView().findViewById(R.id.endDate);
        startDate = getView().findViewById(R.id.textViewStartDate);
        endDate = getView().findViewById(R.id.textViewEndDate);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                startPicker.show(getFragmentManager(), "DATE_PICKER");
            }
        });

        startPicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                startDate.setText(startPicker.getHeaderText());
                startTime = dateFormatter.format(Long.parseLong(startPicker.getSelection().toString()));

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                endPicker.show(getFragmentManager(), "DATE_PICKER");
            }
        });

        endPicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                endDate.setText(endPicker.getHeaderText());
                endTime = dateFormatter.format(Long.parseLong(endPicker.getSelection().toString()));
            }
        });
        final NavController navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setStartTime(startTime);
                viewModel.setEndTime(endTime);
                navController.navigate(R.id.actionEarthQuakeFragment);

            }
        });
    }

}
