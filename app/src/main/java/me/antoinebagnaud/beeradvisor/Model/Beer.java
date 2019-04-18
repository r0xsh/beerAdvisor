package me.antoinebagnaud.beeradvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Beer implements Parcelable {

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

    private int count;

    public Beer(Parcel in) {
        name = in.readString();
        critic = in.readString();
        rate = in.readFloat();
        count = in.readInt();
    }

    public Beer(){    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(critic);
        dest.writeFloat(rate);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

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

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}

