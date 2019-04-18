package me.antoinebagnaud.beeradvisor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.antoinebagnaud.beeradvisor.Adaptor.BeerAdaptor;
import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.Http.BreweryDB;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.Model.Beers;
import me.antoinebagnaud.beeradvisor.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static String EXTRA = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String query = intent.getStringExtra(EXTRA);

        RecyclerView recyclerView = findViewById(R.id.search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new BeerAdaptor(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        BreweryDB b = new BreweryDB();
        b.getService().searchBeer(query).enqueue(new Callback<Beers>() {
            @Override
            public void onResponse(Call<Beers> call, Response<Beers> response) {
                final Beers oui = response.body();
                if (oui.getBeers() != null) {
                    ((BeerAdaptor) adapter).setBeers(oui.getBeers());
                }
            }

            @Override
            public void onFailure(Call<Beers> call, Throwable t) {
                //Log.d(TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }
}
