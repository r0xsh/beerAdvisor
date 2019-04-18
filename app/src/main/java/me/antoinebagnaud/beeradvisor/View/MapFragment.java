package me.antoinebagnaud.beeradvisor.View;

import android.content.Context;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.R;

public class MapFragment extends Fragment {

    private MapView map;
    private MainActivity mainActivity = (MainActivity) getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        map = (MapView) view.findViewById(R.id.map);
        map.setMultiTouchControls(true);

        ItemizedIconOverlay markersOverlay = new
                ItemizedIconOverlay<OverlayItem>(new LinkedList<OverlayItem>(),
                getResources().getDrawable(R.drawable.marker_default), null, getActivity());
        map.getOverlays().add(markersOverlay);

        try {
            for (Beer b: AsyncData.getAll(getActivity())) {
                Marker startMarker = new Marker(map);
                startMarker.setPosition(new GeoPoint(b.getLat(), b.getLng()));
                startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                startMarker.setTitle(b.getName());
                map.getOverlays().add(startMarker);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IMapController mapController = map.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(mainActivity.location.getLatitude(), mainActivity.location.getLongitude());
        mapController.setCenter(startPoint);

        return view;
    }

    public void onResume(){
        super.onResume();
        map.onResume();
    }

    public void onPause(){
        super.onPause();
        map.onPause();
    }
}
