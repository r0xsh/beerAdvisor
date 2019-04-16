package me.antoinebagnaud.beeradvisor;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.Model.BeerDao;

@Database(entities = {Beer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BeerDao beerDao();
}
