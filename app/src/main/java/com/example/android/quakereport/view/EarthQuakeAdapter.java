package com.example.android.quakereport.view;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.EarthQuakeResponse;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthquakeViewHolder> {

    private ArrayList<EarthQuakeResponse.Feature> earthQuakes;


    public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        public EarthquakeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri earthquakeUri = Uri.parse(earthQuakes.get(getAdapterPosition()).getProperties().getUrl());
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                }

            });

        }
    }


    public EarthQuakeAdapter(ArrayList<EarthQuakeResponse.Feature> earthQuakes) {
        this.earthQuakes = earthQuakes;
    }

    public void updateEarthquakes(List<EarthQuakeResponse.Feature> newEarthquakes) {
        earthQuakes.clear();
        earthQuakes.addAll(newEarthquakes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        TextView magnitude = holder.itemView.findViewById(R.id.magnitude);
        TextView location = holder.itemView.findViewById(R.id.location);
        TextView date = holder.itemView.findViewById(R.id.date);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(earthQuakes.get(position).getProperties().getMag()));
        DecimalFormat format = new DecimalFormat("0.0");
        String output = format.format(earthQuakes.get(position).getProperties().getMag());
        magnitude.setText(output);


        location.setText(earthQuakes.get(position).getProperties().getPlace());

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy\nh:mm a", Locale.ENGLISH);
        String dateView = dateFormatter.format(new Date(earthQuakes.get(position).getProperties().getTime()));
        date.setText(dateView);


    }


    @Override
    public int getItemCount() {

        return earthQuakes.size();
    }

    public int getMagnitudeColor(double mag) {
        int magColor = (int) Math.floor(mag);
        switch (magColor) {
            case 1:
                magColor = R.color.magnitude1;
                break;
            case 2:
                magColor = R.color.magnitude2;
                break;
            case 3:
                magColor = R.color.magnitude3;
                break;
            case 4:
                magColor = R.color.magnitude4;
                break;
            case 5:
                magColor = R.color.magnitude5;
                break;
            case 6:
                magColor = R.color.magnitude6;
                break;
            case 7:
                magColor = R.color.magnitude7;
                break;
            case 8:
                magColor = R.color.magnitude8;
                break;
            case 9:
                magColor = R.color.magnitude9;
                break;
            case 10:
                magColor = R.color.magnitude10plus;
                break;

        }
        return magColor;
    }


}
