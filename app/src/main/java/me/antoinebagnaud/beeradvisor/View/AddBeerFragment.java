package me.antoinebagnaud.beeradvisor.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.Model.BeerDao;
import me.antoinebagnaud.beeradvisor.R;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class AddBeerFragment extends Fragment {
    private ImageButton takePicture;
    private Button addBeerButton;
    private EditText beerName;
    private EditText beerCritic;
    private MaterialRatingBar beerRating;
    private ImageView thumbNail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_beer, container, false);
        beerName = view.findViewById(R.id.editTextBeerName);
        beerCritic = view.findViewById(R.id.editTextBeerCritic);
        beerRating = view.findViewById(R.id.materialRatingBar);
        takePicture = view.findViewById(R.id.imageButtonTakePicture);
        takePicture.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){Toast.makeText(getContext(), "Pas encore fait", Toast.LENGTH_LONG).show();}
        });
        addBeerButton = view.findViewById(R.id.buttonValidate);
        addBeerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveBeer();
            }
        });
        return view;
    }

    public void saveBeer(){
        Beer beer = new Beer();
        beer.setName(beerName.getText().toString());
        beer.setCritic(beerCritic.getText().toString());
        beer.setRate(beerRating.getRating());
        beer.setCount(1);
        List<Beer> beerList = new ArrayList<Beer>();
        beerList.add(beer);
        AsyncData.insertBeers(getContext(),beerList);
        ((MainActivity) getActivity()).navigation.setSelectedItemId(R.id.navigation_home);
    }

}
