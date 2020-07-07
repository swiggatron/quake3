
package com.example.android.quakereport.model;

import com.google.android.gms.common.Feature;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

            public Double getMag() {
                return mag;
            }

            public  String getPlace() {
                return place;
            }

            public Long getTime() {
                return time;
            }

            public String getUrl() {
                return url;
            }
        }

    }
}
