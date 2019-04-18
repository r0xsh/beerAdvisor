package me.antoinebagnaud.beeradvisor.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.R;
import me.antoinebagnaud.beeradvisor.View.DetailsActivity;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class BeerAdaptor extends RecyclerView.Adapter<BeerAdaptor.ViewHolder> {

    private Context context;
    private List<Beer> beers;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public View layout;
        public MaterialRatingBar ratingBar;
        public CardView cardView;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            cardView = v.findViewById(R.id.cardViewBeer);
            name = v.findViewById(R.id.beer_name);
            image = v.findViewById(R.id.beer_img);
            ratingBar = v.findViewById(R.id.beer_rate);

        }
    }

    public BeerAdaptor(Context context, List<Beer> beers) {
        this.context = context;
        this.beers = beers;
    }

    public void add(int position, Beer item) {
        beers.add(item);
        notifyItemInserted(position);
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
        notifyDataSetChanged();
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
        holder.ratingBar.setRating(beer.getRate());
        if (beer.getImage() != null) {
            Glide.with(context).load(new File(beer.getImage())).thumbnail(0.5f).centerCrop().into(holder.image);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.EXTRA, beer);
                v.getContext().startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                AsyncData.incrementCount(v.getContext(), beer);
                Toast.makeText(v.getContext(), "Et une de plus !", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }


}
