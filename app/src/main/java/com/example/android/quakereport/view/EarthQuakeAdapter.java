package com.example.android.quakereport.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.databinding.ListItemBinding;
import com.example.android.quakereport.model.EarthQuakeResponse;

import java.util.ArrayList;
import java.util.List;

public class EarthQuakeAdapter extends RecyclerView.Adapter<EarthQuakeAdapter.EarthquakeViewHolder> implements OnQuakeListener {

    private ArrayList<EarthQuakeResponse.Feature> earthQuakes;
    private double mag;


    @Override
    public void onQuakeClicked(View v) {
        String quakeUrl = ((TextView) v.findViewById(R.id.quakeUrl)).getText().toString();
        Uri uri = Uri.parse(quakeUrl);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
        v.getContext().startActivity(webIntent);
    }


    public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        public ListItemBinding itemView;


        public EarthquakeViewHolder(@NonNull ListItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;


        }

    }


    public EarthQuakeAdapter(ArrayList<EarthQuakeResponse.Feature> earthQuakes, Context context) {
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding view = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);

        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, final int position) {
        holder.itemView.setEarthquake(earthQuakes.get(position));
        holder.itemView.setListener(this);
        mag = Double.parseDouble(earthQuakes.get(position).getProperties().getMag());
//        TextView magnitude = holder.itemView.findViewById(R.id.magnitude);
//        TextView location = holder.itemView.findViewById(R.id.location);
//        TextView date = holder.itemView.findViewById(R.id.date);
//
//        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
//        magnitudeCircle.setColor(getMagnitudeColor(earthQuakes.get(position).getProperties().getMag()));
//        DecimalFormat format = new DecimalFormat("0.0");
//        String output = format.format(earthQuakes.get(position).getProperties().getMag());
//        magnitude.setText(output);
//
//
//        location.setText(earthQuakes.get(position).getProperties().getPlace());
//
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy\nh:mm a", Locale.ENGLISH);
//       String dateView = dateFormatter.format(new Date(earthQuakes.get(position).getProperties().getTime()));
//        date.setText(dateView);
//


    }


    @Override
    public int getItemCount() {

        return earthQuakes.size();
    }

    public int getMagnitudeColor(Context context) {


        int magColorResourceId;
        int magColor = (int) Math.floor(mag);
        switch (magColor) {
            case 1:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 2:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude2);
                break;
            case 3:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude3);
                break;
            case 4:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude4);
                break;
            case 5:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude5);
                break;
            case 6:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude6);
                break;
            case 7:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude7);
                break;
            case 8:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude8);
                break;
            case 9:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude9);
                break;
            case 10:
                magColorResourceId = ContextCompat.getColor(context, R.color.magnitude10plus);
                break;
            default:
                magColorResourceId = ContextCompat.getColor(context, R.color.colorPrimary);

        }

        return magColorResourceId;
    }


}
