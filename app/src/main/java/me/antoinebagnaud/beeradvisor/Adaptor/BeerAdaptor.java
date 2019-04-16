package me.antoinebagnaud.beeradvisor.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.R;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class BeerAdaptor extends RecyclerView.Adapter<BeerAdaptor.ViewHolder> {

    private List<Beer> beers;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public View layout;
        public MaterialRatingBar ratingBar;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = v.findViewById(R.id.beer_name);
            image = v.findViewById(R.id.beer_img);
            ratingBar = v.findViewById(R.id.beer_rate);

        }
    }

    public BeerAdaptor(List<Beer> beers) {
        this.beers = beers;
    }

    public void add(int position, Beer item) {
        beers.add(item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        beers.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.beer_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Beer beer = beers.get(position);
        holder.name.setText(beer.getName());
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }


}
