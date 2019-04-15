package me.antoinebagnaud.beeradvisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Beers {

    @SerializedName("data")
    @Expose
    private List<Beer> data;

    public List<Beer> getData() {
        return data;
    }

    public void setData(List<Beer> data) {
        this.data = data;
    }
}
