
package com.example.android.quakereport.model;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EarthQuakeResponse {

    @SerializedName("type")
    private String type;

    @SerializedName("features")
    private List<Feature> features;

    @SerializedName("bbox")
    private List<Double> bbox;

    public String getType() {
        return type;
    }


    public List<Feature> getFeatures() {
        return features;
    }


    public List<Double> getBbox() {
        return bbox;
    }


    public class Feature {

        @SerializedName("type")
        private String type;

        @SerializedName("properties")
        private Properties properties;

        @SerializedName("id")
        private String id;

        public String getType() {
            return type;
        }


        public Properties getProperties() {
            return properties;
        }


        public String getId() {
            return id;
        }


        public class Properties {

            @SerializedName("mag")
            private Double mag;

            @SerializedName("place")
            private String place;

            @SerializedName("time")
            private Long time;

            @SerializedName("url")
            private String url;

            public String getMag() {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(mag);
            }

            public String getPlace() {
                return place;
            }

            public String getTime() {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy\nh:mm a", Locale.ENGLISH);
                return dateFormatter.format(time);
            }

            public String getUrl() {
                return url;
            }
        }

    }
}
