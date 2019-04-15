package me.antoinebagnaud.beeradvisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Beers {

    @SerializedName("data")
    @Expose
    private List<Beer> beers;

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
