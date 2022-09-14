package my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favoritesTable")
    fun getAllFavorites(): LiveData<List<FavoritesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(FavoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favoritesTable WHERE favoriteCurrencyName = :favoriteCurrencyName")
    suspend fun getFavoriteByName(favoriteCurrencyName: String): FavoritesEntity

    @Query("DELETE FROM favoritesTable WHERE favoriteCurrencyName = :favoriteCurrencyName")
    suspend fun deleteFavoriteByName(favoriteCurrencyName: String)
}