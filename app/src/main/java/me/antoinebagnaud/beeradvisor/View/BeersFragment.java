package me.antoinebagnaud.beeradvisor.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.antoinebagnaud.beeradvisor.Adaptor.BeerAdaptor;
import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.R;

public class BeersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beers, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.beers_in_db);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        try {
            BeerAdaptor beerAdaptor = new BeerAdaptor(AsyncData.getAll(view.getContext()));
            recyclerView.setAdapter(beerAdaptor);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        return view;
    }

}
