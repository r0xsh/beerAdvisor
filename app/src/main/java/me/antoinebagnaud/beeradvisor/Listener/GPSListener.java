package me.antoinebagnaud.beeradvisor.Listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.function.Function;

public final class GPSListener implements LocationListener {

    private static Location location;

    private List<LocationCallback> callbacks;

    public abstract class LocationCallback {
        private Context context;

        LocationCallback(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        abstract void onLocation(Context context, Location location);
    }

    @Override
    public void onLocationChanged(Location location) {
        for (LocationCallback locationCallback: callbacks) {
            locationCallback.onLocation(locationCallback.getContext(), location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
