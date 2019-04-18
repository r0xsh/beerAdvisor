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
}

