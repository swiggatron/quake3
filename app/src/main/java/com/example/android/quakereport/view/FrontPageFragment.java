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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.android.quakereport.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FrontPageFragment extends Fragment {


    String sTime;
    String eTime;
    String startTime;
    String endTime;
    MaterialDatePicker.Builder builder;
    MaterialDatePicker startPicker;
    MaterialDatePicker endPicker;
    TextView start;
    TextView end;
    TextView startDate;
    TextView endDate;
    @BindView(R.id.search_button)
    Button search;


    public FrontPageFragment() {
        //required empty public constructor
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
                sTime = (startPicker.getSelection().toString());

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
                eTime = (endPicker.getSelection().toString());
            }
        });
        final NavController navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.actionEarthQuakeFragment);
            }
        });
    }

    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public String getStartTime() throws ParseException {
        Date sDate = dateFormatter.parse(sTime);
        assert sDate != null;
        startTime = dateFormatter.format(sDate);
        return startTime;
    }

    public String getEndTime() throws ParseException {
        Date eDate = dateFormatter.parse(eTime);
        assert eDate != null;
        endTime = dateFormatter.format(eDate);
        return endTime;
    }
}
