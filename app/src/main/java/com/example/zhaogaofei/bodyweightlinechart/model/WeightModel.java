package com.example.zhaogaofei.bodyweightlinechart.model;

import android.support.annotation.NonNull;
import android.util.Log;

public class WeightModel implements Comparable<WeightModel> {
    private String time;
    private String weight;

    public WeightModel(String time, String weight) {
        this.time = time;
        this.weight = weight;
    }

    public WeightModel() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(@NonNull WeightModel model) {
        int thisTime = Integer.parseInt(this.time);
        int oTime = Integer.parseInt(model.time);
        if (thisTime == oTime) {
            return 0;
        } else {
            return thisTime > oTime ? 1 : -1;
        }
    }
}
