package me.antoinebagnaud.beeradvisor.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import me.antoinebagnaud.beeradvisor.R;

public class MainActivity extends AppCompatActivity {


    //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BreweryDB b = new BreweryDB();
//                b.getService().searchBeer("ipa").enqueue(new Callback<BeersFragment>() {
//                    @Override
//                    public void onResponse(Call<BeersFragment> call, Response<BeersFragment> response) {
//                        final BeersFragment oui = response.body();
//
//                        AsyncData.insertBeers(getContext(), oui.getBeers());
//                    }
//
//                    @Override
//                    public void onFailure(Call<BeersFragment> call, Throwable t) {
//                        //Log.d(TAG, "onFailure: " + t.getMessage());
//                        t.printStackTrace();
//                    }
//                });
//            }
//        });

    private TextView mTextMessage;
    private FragmentManager fragmentManager;

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
                    mTextMessage.setText(R.string.title_dashboard);
                    break;
                case R.id.navigation_notifications:
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

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container_frag, new BeersFragment(), "TAMER")
                .commit();


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
