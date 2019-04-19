package me.antoinebagnaud.beeradvisor.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import me.antoinebagnaud.beeradvisor.Listener.GPSListener;
import me.antoinebagnaud.beeradvisor.R;

import static me.antoinebagnaud.beeradvisor.View.AddBeerFragment.REQUEST_TAKE_PHOTO;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private SearchView searchView;
    private Fragment fragment;
    public BottomNavigationView navigation;
    public static Location location;

    public Context getContext() {
        return (Context) this;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new BeersFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new AddBeerFragment();
                    break;
                case R.id.navigation_maps:
                    fragment = new MapFragment();
                    break;
            }
            assert fragment != null;
            fragmentManager.beginTransaction()
                    .replace(R.id.container_frag, fragment, "TAMER")
                    .commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container_frag, new BeersFragment(), "TAMER")
                .commit();


        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 3);

       initLocation();


    }

    private void initLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GPSListener());


        GPSListener.addCallback(new GPSListener.LocationCallback(this) {
            @Override
            protected void onLocation(Context context, Location location) {
                Log.d(TAG, "onLocationChanged: " + location.toString());
                if (MainActivity.location == null) {
                    ((MainActivity)context).navigation.getMenu().findItem(R.id.navigation_maps).setEnabled(true);
                }
                MainActivity.location = location;
            }
        }, 12);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 3)  {
            for (int p: grantResults) {
                if (p != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }

            initLocation();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment: " + fragment.getClass().getName());
        fragment = fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra(SearchActivity.EXTRA, query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }



}
