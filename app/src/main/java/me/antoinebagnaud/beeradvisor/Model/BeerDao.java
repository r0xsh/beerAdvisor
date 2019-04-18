package me.antoinebagnaud.beeradvisor.Model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface BeerDao {
    @Query("SELECT * FROM beer")
    List<Beer> getAll();

    @Query("SELECT * FROM beer WHERE name IN (:userIds)")
    List<Beer> loadAllByIds(String[] userIds);

    @Query("SELECT * FROM beer WHERE name LIKE :name LIMIT 1")
    Beer findByName(String name);

    @Insert
    void insertAll(Beer... users);

    @Delete
    void delete(Beer user);
}
