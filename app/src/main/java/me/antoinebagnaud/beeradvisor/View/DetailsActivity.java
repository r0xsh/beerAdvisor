package me.antoinebagnaud.beeradvisor.View;

import androidx.appcompat.app.AppCompatActivity;
import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.R;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {
    public final static String EXTRA = "beer";
    private TextView beerName;
    private TextView beerCritic;
    private TextView beerCount;
    private ImageView beerPicture;
    private MaterialRatingBar beerRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Choper la biere en bd avec le nom;
        beerName = findViewById(R.id.textViewBeerName);
        beerCritic = findViewById(R.id.textViewBeerCritic);
        beerCount = findViewById(R.id.textViewBeerCount);
        beerPicture = findViewById(R.id.imageViewBeerPicture);
        beerRating = findViewById(R.id.materialRatingBarBeerRate);

        Intent intent = getIntent();
        Beer beer = (Beer) intent.getParcelableExtra(EXTRA);
        try {
            beer = AsyncData.findByName(this, beer);
            beerName.setText(beer.getName());
            beerCritic.setText(beer.getCritic());
            beerRating.setRating(beer.getRate());
            Glide.with(this).load(new File(beer.getImage())).into(beerPicture);
            beerCount.setText(Integer.toString(beer.getCount()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
