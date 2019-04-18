package me.antoinebagnaud.beeradvisor;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.room.Room;
import me.antoinebagnaud.beeradvisor.Model.Beer;

public class AsyncData {

    private static AppDatabase db = null;

    /**
     *
     * @param ctx
     * @return
     */
    private static AppDatabase getDb(Context ctx) {
        if (db == null) {
            db = Room.databaseBuilder(ctx.getApplicationContext(),
                    AppDatabase.class, "beerAdvisor").build();
        }
        return db;
    }

    /**
     *
     * @param context
     * @param beers
     */
    public static void insertBeers(final Context context, final List<Beer> beers) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                getDb(context).beerDao().insertAll((Beer[]) beers.stream().toArray(Beer[]::new));
                return null;
            }
        }.execute();
    }


    /**
     *
     * @param context
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static List<Beer> getAll(final Context context) throws ExecutionException, InterruptedException {
        AsyncTask<Void, Void, List<Beer>> task = new AsyncTask<Void, Void, List<Beer>>() {
            @Override
            protected List<Beer> doInBackground(Void... voids) {
                return getDb(context).beerDao().getAll();

            }
        };
        return task.execute().get();
    }

    public static Beer findByName(final Context context, Beer beer) throws ExecutionException, InterruptedException {
        AsyncTask<Void, Void, Beer> task = new AsyncTask<Void, Void, Beer>() {
            @Override
            protected Beer doInBackground(Void... voids) {
                return getDb(context).beerDao().findByName(beer.getName());

            }
        };
        return task.execute().get();
    }
}
