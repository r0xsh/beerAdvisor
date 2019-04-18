package me.antoinebagnaud.beeradvisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Beer {

    @SerializedName("name")
    @PrimaryKey
    @NonNull
    @Expose
    private String name;

    private String critic;

    private float rate;

    private String image;

    private float lat;

    private float lng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCritic() { return critic; }

    public void setCritic(String critic) { this.critic = critic; }

    public float getRate() { return rate; }

    public void setRate(float rate) { this.rate = rate; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}

