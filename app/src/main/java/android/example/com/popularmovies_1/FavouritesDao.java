package android.example.com.popularmovies_1;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavouritesDao {

    @Query("SELECT * FROM favourites ORDER BY id")

 //   FavourtiesEntry[] loadAllFavourites();
    List<FavourtiesEntry> loadAllFavourites();

    @Insert
    void insertFavourite (FavourtiesEntry favourtiesEntry);

    @Delete
    void deleteFavourite (FavourtiesEntry favourtiesEntry);

}
