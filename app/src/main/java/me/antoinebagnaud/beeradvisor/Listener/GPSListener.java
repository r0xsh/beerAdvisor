package me.antoinebagnaud.beeradvisor.Listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public final class GPSListener implements LocationListener {

    private static Location location;

    private static List<LocationCallback> callbacks = new ArrayList<>();

    public abstract static class LocationCallback {
        private Context context;
        private int callbackSignature;

        public LocationCallback(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }

        public int getCallbackSignature() {
            return callbackSignature;
        }

        public void setCallbackSignature(int callbackSignature) {
            this.callbackSignature = callbackSignature;
        }

        protected abstract void onLocation(Context context, Location location);
    }

    public static void addCallback(LocationCallback locationCallback, int callbackSignature) {
        for (LocationCallback callback: GPSListener.callbacks) {
            if (callback.getCallbackSignature() == callbackSignature) {
                return;
            }
        }
        locationCallback.setCallbackSignature(callbackSignature);
        GPSListener.callbacks.add(locationCallback);
    }

    public static Location getLocation() {
        return GPSListener.location;
    }

    @Override
    public void onLocationChanged(Location location) {
        GPSListener.location = location;
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
